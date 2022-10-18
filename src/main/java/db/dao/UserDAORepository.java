package db.dao;

import db.DBManager;
import db.entity.User;
import db.entity.dto.UserDTO;
import exception.DBException;
import org.apache.log4j.Logger;
import util.DBConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAORepository {

    private final Logger LOGGER = Logger.getLogger(UserDAORepository.class);

    private final DBManager dbManager;

    public UserDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public User getUser(User user) throws DBException { // TODO check by password
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User findUser = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_GET_USER_BY_LOGIN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                findUser = extractUser(resultSet);
            }
            connection.commit();
            LOGGER.info("User: " + user);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, preparedStatement, resultSet);
        }
        return findUser;
    }

    public List<UserDTO> getUserDTOList() throws DBException {
        List<UserDTO> userDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstant.SQL_GET_USER_FROM_TABLE);
            while (resultSet.next()) {
                userDTOList.add(extractUserDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Users DTO list: " + userDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain users DTO list ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return userDTOList;
    }

    public boolean insertUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_USER);
            int i = 1;
            System.out.println(user);
            preparedStatement.setString(i++, user.getLogin());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setLong(i, user.getRoleId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + user.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public boolean updateUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_USER);
            int i = 1;
            System.out.println(user.getId() + ": " + user);
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setInt(i, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("User with id " + user.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId((resultSet.getInt("id")));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setRoleId(resultSet.getInt("role_id"));
        return user;
    }

    private UserDTO extractUserDTO(ResultSet resultSet) throws SQLException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId((resultSet.getInt("id")));
        userDTO.setLogin(resultSet.getString("login"));
        userDTO.setPassword(resultSet.getString("password"));
        userDTO.setFirstName(resultSet.getString("firstname"));
        userDTO.setLastName(resultSet.getString("lastname"));
        userDTO.setRole_id(resultSet.getInt("role_id"));
        return userDTO;
    }
}