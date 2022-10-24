package db.dao;

import db.DBManager;
import db.entity.Event;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAORepository {

    private final Logger LOGGER = Logger.getLogger(EventDAORepository.class);

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
                    "SELECT * FROM events WHERE id=?",  // TODO QUERY
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, event.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                selectedEvent = extractEvent(resultSet);
            }
            connection.commit();
            LOGGER.info("Selected event: " + selectedEvent);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain event ", e);
            throw new DBException("Unable to connect", e);
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
                    "INSERT INTO events(name, place, date, time) VALUES (?,?,?,?)"); // TODO QUERY

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, event.getName());
            preparedStatement.setString(columnIndex++, event.getPlace());
            preparedStatement.setString(columnIndex++, event.getDate());
            preparedStatement.setString(columnIndex, event.getTime());

            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Inserted event: " + event);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert event ", e);
            throw new DBException("Unable to connect", e);
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
                    "UPDATE events name=?, place=?, date=?, time=? WHERE id=?"); // TODO QUERY

            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, event.getName());
            preparedStatement.setString(columnIndex++, event.getPlace());
            preparedStatement.setString(columnIndex++, event.getDate());
            preparedStatement.setString(columnIndex++, event.getTime());
            preparedStatement.setInt(columnIndex, event.getId());

            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Updated event (id: " + event.getId() + "): " + event);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update event ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            DBManager.close(connection, preparedStatement);
        }
        return true;
    }

    public List<EventDTO> getEventDTOList() throws DBException {
        List<EventDTO> eventDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM events;"); // TODO QUERY
            while (resultSet.next()) {
                eventDTOList.add(extractEventDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Event DTO list size: " + eventDTOList.size());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain event DTO list ", e);
            throw new DBException("Unable to connect", e);
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