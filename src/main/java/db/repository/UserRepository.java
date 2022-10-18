package db.repository;

import db.entity.User;
import db.entity.dto.UserDTO;

import java.util.List;

public interface UserRepository {

    User getUser(User user);

    void createUser(User user);

    void updateUser(User user);

    List<UserDTO> getUserDTOList();
}