package db.repository.impl;

import db.DBManager;
import db.dao.UserDAORepository;
import db.entity.User;
import db.entity.dto.UserDTO;
import db.repository.UserRepository;
import exception.DBException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    private final UserDAORepository userDAORepository;

    private final DBManager dbManager;

    public UserRepositoryImpl(UserDAORepository userDAORepository, DBManager dbManager) {
        this.userDAORepository = userDAORepository;
        this.dbManager = dbManager;
    }

    @Override
    public User getUser(User user) {
        return dbManager.doTransaction(() -> {
            try {
                return userDAORepository.selectUser(user);
            } catch (DBException ex) {
                LOGGER.error("Cannot get user " + ex);
            }
            return new User();
        });
    }

    @Override
    public void createUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                return userDAORepository.insertUser(user);
            } catch (DBException ex) {
                LOGGER.error("Cannot create new user " + ex);
            }
            return false;
        });
    }

    @Override
    public void updateUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                return userDAORepository.updateUser(user);
            } catch (DBException ex) {
                LOGGER.error("Cannot update user " + ex);
            }
            return false;
        });
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return dbManager.doTransaction(() -> {
            try {
                return userDAORepository.getUserDTOList();
            } catch (DBException ex) {
                LOGGER.error("Cannot get list user " + ex);
            }
            return new ArrayList<>();
        });
    }
}