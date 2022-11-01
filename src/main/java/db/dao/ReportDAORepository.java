package db.dao;

import db.DBManager;
import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import db.entity.dto.UserDTO;
import exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAORepository {

    private final Logger LOGGER = Logger.getLogger(ReportDAORepository.class);

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
                    "SELECT * FROM reports WHERE event_id=? and topic=?",  // TODO QUERY
                    Statement.RETURN_GENERATED_KEYS);
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, report.getEventId());
            preparedStatement.setString(columnIndex, report.getTopic());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectedReport = extractReport(resultSet);
            }
            connection.commit();
            LOGGER.info("Selected report: " + selectedReport);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report ", e);
            throw new DBException("Unable to connect", e);
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
                    "INSERT INTO reports(topic, event_id, speaker_id) VALUES (?,?,?)"); // TODO QUERY
            System.out.println(report);
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, report.getTopic());
            preparedStatement.setInt(columnIndex++, report.getEventId());
            preparedStatement.setInt(columnIndex, report.getSpeakerId());

            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Inserted report: " + report);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert report ", e);
            throw new DBException("Unable to connect", e);
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
                    "UPDATE reports topic=?, event_id=?, speaker_id=? WHERE id=?"); // TODO QUERY

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, report.getTopic());
            preparedStatement.setInt(columnIndex++, report.getEventId());
            preparedStatement.setInt(columnIndex++, report.getSpeakerId());
            preparedStatement.setInt(columnIndex, report.getId());

            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Updated report (id: " + report.getId() + "): " + report);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update report ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
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
                            "INNER JOIN users ON reports.speaker_id = users.id"); // TODO QUERY

            while (resultSet.next()) {
                reportDTOList.add(extractReportDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Report DTO list size: " + reportDTOList.size());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report DTO list ", e);
            throw new DBException("Unable to connect", e);
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
                            "WHERE reports.event_id = " + eventDTO.getId()); // TODO QUERY

            while (resultSet.next()) {
                reportDTOList.add(extractReportDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Report DTO list size: " + reportDTOList.size());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain report DTO list ", e);
            throw new DBException("Unable to connect", e);
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