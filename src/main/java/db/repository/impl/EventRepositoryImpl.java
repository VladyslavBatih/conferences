package db.repository.impl;

import db.DBManager;
import db.dao.EventDAORepository;
import db.entity.Event;
import db.entity.dto.EventDTO;
import db.repository.EventRepository;
import exception.DBException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private static final Logger LOGGER = Logger.getLogger(EventRepositoryImpl.class);

    private final EventDAORepository eventDAORepository;

    private final DBManager dbManager;

    public EventRepositoryImpl(EventDAORepository eventDAORepository, DBManager dbManager) {
        this.eventDAORepository = eventDAORepository;
        this.dbManager = dbManager;
    }

    @Override
    public Event getEvent(Event event) {
        return dbManager.doTransaction(() -> {
            try {
                return eventDAORepository.selectEvent(event);
            } catch (DBException ex) {
                LOGGER.error("Cannot get event " + ex);
            }
            return null;
        });
    }

    @Override
    public void createEvent(Event event) {
        dbManager.doTransaction(() -> {
            try {
                return eventDAORepository.insertEvent(event);
            } catch (DBException ex) {
                LOGGER.error("Cannot create new event " + ex);
            }
            return false;
        });
    }

    @Override
    public void updateEvent(Event event) {
        dbManager.doTransaction(() -> {
            try {
                return eventDAORepository.updateEvent(event);
            } catch (DBException ex) {
                LOGGER.error("Cannot update event " + ex);
            }
            return false;
        });
    }

    @Override
    public void removeEvent(int id) {
        dbManager.doTransaction(() -> {
            try {
                eventDAORepository.deleteEvent(id);
            } catch (DBException ex) {
                LOGGER.error("Cannot remove event " + ex);
            }
            return false;
        });
    }

    @Override
    public List<EventDTO> getEventDTOList() {
        return dbManager.doTransaction(() -> {
            try {
                return eventDAORepository.getEventDTOList();
            } catch (DBException ex) {
                LOGGER.error("Cannot get list event " + ex);
            }
            return new ArrayList<>();
        });
    }
}