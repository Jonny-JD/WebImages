package dao;

import dto.UserDTO;
import entity.User;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;
import util.PasswordEncoder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDAO {

    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            SELECT id, name, email FROM users
            WHERE email = ?
            AND password = ?
            """;
    private static final String ADD_NEW_USER = """
            INSERT INTO users (name, email, password)
            VALUES (?, ?, ?)
            """;

    private static final String ADD_SALT = """
                INSERT INTO salt (id, salt)
                VALUES (?, ?)
            """;
    private static final String GET_SALT = """
            SELECT s.salt FROM salt
            JOIN users u ON u.email = ?
                JOIN salt s ON u.id = s.id
            """;
    private static final UserDAO INSTANCE = new UserDAO();

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = null;
        try (var connection = ConnectionManager.get();
             var preparedStatementSalt = connection.prepareStatement(GET_SALT);
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {

            preparedStatementSalt.setString(1, email);
            var resultSetSalt = preparedStatementSalt.executeQuery();

            if (resultSetSalt.next()) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, PasswordEncoder.generatePassword(password, resultSetSalt.getString("salt")));

                var resultSet = preparedStatement.executeQuery();
                resultSet.next();

                user = buildUserEntity(resultSet);
            }

            return Optional.ofNullable(user);
        }

    }

    @SneakyThrows
    public User addNewUser(UserDTO userDTO) {
        var salt = PasswordEncoder.generateSalt();

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(ADD_NEW_USER, RETURN_GENERATED_KEYS);
             var preparedStatementSalt = connection.prepareStatement(ADD_SALT)) {
            preparedStatement.setString(1, userDTO.getName());
            preparedStatement.setString(2, userDTO.getEmail());
            preparedStatement.setString(3, PasswordEncoder.generatePassword(userDTO.getPassword(), salt));

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();
            preparedStatementSalt.setLong(1, generatedKeys.getObject("id", Long.class));
            preparedStatementSalt.setString(2, salt);

            preparedStatementSalt.executeUpdate();

            return User.builder()
                    .id(generatedKeys.getObject("id", BigInteger.class))
                    .email(userDTO.getEmail())
                    .name(userDTO.getName())
                    .build();

        }
    }


    @SneakyThrows
    private static User buildUserEntity(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getObject("id", BigInteger.class))
                .name(resultSet.getObject("name", String.class))
                .email(resultSet.getObject("email", String.class))
                .build();
    }
}
