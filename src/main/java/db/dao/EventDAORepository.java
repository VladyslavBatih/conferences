package db.dao;

import db.DBManager;
import db.entity.Event;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDAORepository {

    private static final Logger LOGGER = Logger.getLogger(EventDAORepository.class);

    private final DBManager dbManager;

    public EventDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Event selectEvent(Event event) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Event selectedEvent = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM events WHERE name=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getName());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectedEvent = extractEvent(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain event ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement, resultSet);
        }
        return selectedEvent;
    }

    public boolean insertEvent(Event event) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO events(name, place, date, time) VALUES (?,?,?,?)");

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, event.getName());
            preparedStatement.setString(columnIndex++, event.getPlace());
            preparedStatement.setString(columnIndex++, event.getDate());
            preparedStatement.setString(columnIndex, event.getTime());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert event ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public boolean updateEvent(Event event) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE events SET name=?, place=?, date=?, time=? WHERE id=?");

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, event.getName());
            preparedStatement.setString(columnIndex++, event.getPlace());
            preparedStatement.setString(columnIndex++, event.getDate());
            preparedStatement.setString(columnIndex++, event.getTime());
            preparedStatement.setInt(columnIndex, event.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update event ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public void deleteEvent(int id) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM events WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain delete event ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
    }

    public List<EventDTO> getEventDTOList() throws DBException {
        List<EventDTO> eventDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM events;");
            while (resultSet.next()) {
                eventDTOList.add(extractEventDTO(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain event DTO list ", ex);
            throw new DBException("Unable to connect", ex);
        } finally {
            DBManager.close(connection, statement, resultSet);
        }
        return eventDTOList;
    }

    private Event extractEvent(ResultSet resultSet) throws SQLException {
        Event event = new Event(
                resultSet.getString("name"),
                resultSet.getString("place"),
                resultSet.getString("date"),
                resultSet.getString("time"));
        event.setId(resultSet.getInt("id"));
        return event;
    }

    private EventDTO extractEventDTO(ResultSet resultSet) throws SQLException, DBException {
        EventDTO eventDTO = new EventDTO(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("place"),
                resultSet.getString("date"),
                resultSet.getString("time"),
                new ArrayList<>());
        List<ReportDTO> reportDTOList = getReportDTOListByEventDTO(eventDTO);
        eventDTO.getReportDTOList().addAll(reportDTOList);
        return eventDTO;
    }

    private List<ReportDTO> getReportDTOListByEventDTO(EventDTO eventDTO) throws DBException {
        ReportDAORepository reportDAORepository = new ReportDAORepository(dbManager);
        return reportDAORepository.getReportDTOList(eventDTO);
    }
}