package db;

import org.apache.log4j.Logger;
import util.LoggerUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;

public class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class);

    private static DBManager instance;
    private DataSource dataSource;

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/conferences");
        } catch (NamingException ex) {
            LOGGER.error(LoggerUtil.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(LoggerUtil.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            return null;
        }
    }

    public <T> T doTransaction(Supplier<T> function) {
        T result = null;
        Connection connection = getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            result = function.get();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error(LoggerUtil.ERR_FAIL_TRANSACTION, e);
        } finally {
            close(connection);
        }
        return result;
    }

    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Cannot rollback transaction", e);
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        close(connection);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        close(connection, statement);
    }
}