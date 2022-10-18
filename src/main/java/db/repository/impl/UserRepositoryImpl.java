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

    private final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

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
                LOGGER.info("User was get" + user);
                return userDAORepository.getUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot get user " + e);
            }
            return null;
        });
    }

    @Override
    public void createUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create user: " + user);
                return userDAORepository.insertUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot create new user " + e);
            }
            return false;
        });
    }

    @Override
    public void updateUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update user: " + user);
                return userDAORepository.updateUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot update user " + e);
            }
            return false;
        });
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with user DTO was get");
                return userDAORepository.getUserDTOList();
            } catch (DBException e) {
                LOGGER.error("Cannot get list user " + e);
            }
            return new ArrayList<>();
        });
    }
}