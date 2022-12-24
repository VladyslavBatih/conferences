package db.repository;

import db.entity.Event;
import db.entity.dto.EventDTO;

import java.util.List;

public interface EventRepository {

    Event getEvent(Event event);

    void createEvent(Event event);

    void updateEvent(Event event);

    void removeEvent(int id);

    List<EventDTO> getEventDTOList();
}