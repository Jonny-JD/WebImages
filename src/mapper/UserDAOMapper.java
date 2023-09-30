package mapper;

import dto.UserDTO;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDAOMapper implements Mapper <User, UserDTO> {
    private static final UserDAOMapper INSTANCE = new UserDAOMapper();

    public static UserDAOMapper getInstance() {
        return INSTANCE;
    }
    @Override
    public UserDTO map(User object) {
        return UserDTO.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .build();
    }
}
