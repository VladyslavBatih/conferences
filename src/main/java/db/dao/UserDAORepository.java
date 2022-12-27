package db.dao;

import db.DBManager;
import db.entity.User;
import db.entity.dto.ReportDTO;
import db.entity.dto.UserDTO;
import exception.DBException;
import org.apache.log4j.Logger;
import util.DBConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAORepository {

    private final Logger LOGGER = Logger.getLogger(UserDAORepository.class);

    private final DBManager dbManager;

    public UserDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public User selectUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User selectedUser = null;
        try {
            preparedStatement = connection.prepareStatement(
                    DBConstant.SQL_GET_USER_BY_LOGIN,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectedUser = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain user ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement, resultSet);
        }
        return selectedUser;
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
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain user DTO list ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return userDTOList;
    }

    public List<UserDTO> getUserDTOList(ReportDTO reportDTO) throws DBException {
        List<UserDTO> userDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM reports_users WHERE report_id = " + reportDTO.getId());
            while (resultSet.next()) {
                userDTOList.add(extractUserDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain user DTO list ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return userDTOList;
    }

    public boolean insertUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            int columnIndex = 1;
            preparedStatement = connection.prepareStatement(DBConstant.SQL_INSERT_USER);
            preparedStatement.setString(columnIndex++, user.getLogin());
            preparedStatement.setString(columnIndex++, user.getPassword());
            preparedStatement.setString(columnIndex++, user.getFirstName());
            preparedStatement.setString(columnIndex++, user.getLastName());
            preparedStatement.setInt(columnIndex, user.getRoleId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert user ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public boolean updateUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            int columnIndex = 1;
            preparedStatement = connection.prepareStatement(DBConstant.SQL_UPDATE_USER);
            preparedStatement.setString(columnIndex++, user.getFirstName());
            preparedStatement.setString(columnIndex++, user.getLastName());
            preparedStatement.setInt(columnIndex, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update user ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getInt("role_id"));
        user.setId((resultSet.getInt("id")));
        return user;
    }

    private UserDTO extractUserDTO(ResultSet resultSet) throws SQLException {
        return new UserDTO(
                resultSet.getInt("id"),
                resultSet.getString("login"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"));
    }
}