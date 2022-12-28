package db.dao;

import db.DBManager;
import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import db.entity.dto.UserDTO;
import exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAORepository {

    private static final Logger LOGGER = Logger.getLogger(ReportDAORepository.class);

    private final DBManager dbManager;

    public ReportDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Report selectReport(Report report) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Report selectedReport = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM reports WHERE event_id=? and topic=?",
                    Statement.RETURN_GENERATED_KEYS);
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, report.getEventId());
            preparedStatement.setString(columnIndex, report.getTopic());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectedReport = extractReport(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement, resultSet);
        }
        return selectedReport;
    }

    public boolean insertReport(Report report) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO reports(topic, event_id, speaker_id) VALUES (?,?,?)");
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, report.getTopic());
            preparedStatement.setInt(columnIndex++, report.getEventId());
            preparedStatement.setInt(columnIndex, report.getSpeakerId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert report ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public boolean updateReport(Report report) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE reports SET topic=? WHERE id=?");

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, report.getTopic());
            preparedStatement.setInt(columnIndex, report.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update report ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public void deleteReport(int id) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM reports WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain delete report ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
    }

    public List<ReportDTO> getReportDTOList() throws DBException {
        List<ReportDTO> reportDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT reports.id, reports.topic, users.firstname, users.lastname " +
                            "FROM reports " +
                            "INNER JOIN users ON reports.speaker_id = users.id");

            while (resultSet.next()) {
                reportDTOList.add(extractReportDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report DTO list ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return reportDTOList;
    }

    public List<ReportDTO> getReportDTOList(EventDTO eventDTO) throws DBException {
        List<ReportDTO> reportDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT reports.id, reports.topic, users.firstname, users.lastname " +
                            "FROM reports " +
                            "INNER JOIN users ON reports.speaker_id = users.id " +
                            "WHERE reports.event_id = " + eventDTO.getId());

            while (resultSet.next()) {
                reportDTOList.add(extractReportDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report DTO list ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return reportDTOList;
    }

    private Report extractReport(ResultSet resultSet) throws SQLException {
        Report report = new Report(
                resultSet.getString("topic"),
                resultSet.getInt("event_id"),
                resultSet.getInt("speaker_id"));
        report.setId(resultSet.getInt("id"));
        return report;
    }

    private ReportDTO extractReportDTO(ResultSet resultSet) throws SQLException, DBException {
        ReportDTO reportDTO = new ReportDTO(
                resultSet.getInt("id"),
                resultSet.getString("topic"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                new ArrayList<>());
        List<UserDTO> userDTOList = getUserDTOListByReportDTO(reportDTO);
        reportDTO.getUserDTOList().addAll(userDTOList);
        return reportDTO;
    }

    private List<UserDTO> getUserDTOListByReportDTO(ReportDTO reportDTO) throws DBException {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        return userDAORepository.getUserDTOList(reportDTO);
    }
}