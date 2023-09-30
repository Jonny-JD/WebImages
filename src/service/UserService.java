package service;

import dao.UserDAO;
import dto.UserDTO;
import lombok.NoArgsConstructor;
import mapper.UserDAOMapper;

import java.math.BigInteger;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final UserDAOMapper userDaoMapper = UserDAOMapper.getInstance();

    private static final UserDAO userDAO = UserDAO.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<UserDTO> login(UserDTO userDTO) {
        return userDAO.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword())
                .map(userDaoMapper::map);
    }

    public BigInteger addNewUser(UserDTO userDTO) {
        return userDAO.addNewUser(userDTO).getId();
    }
}
