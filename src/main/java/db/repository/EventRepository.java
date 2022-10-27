package db.repository;

import db.entity.Event;
import db.entity.dto.EventDTO;
import web.bean.EventBean;

import java.util.List;

public interface EventRepository {

    Event getEvent(Event event);

    void createEvent(Event event);

    void updateEvent(Event event);

    List<EventDTO> getEventDTOList();
}