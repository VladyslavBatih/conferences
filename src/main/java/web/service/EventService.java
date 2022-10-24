package web.service;

import db.entity.Event;
import db.entity.dto.EventDTO;

import java.util.List;

public interface EventService {

    void addEvent(Event event);

    void updateEventInfo(Event event);

    Event findEvent(Event event);

    List<EventDTO> getEventDTOList();
}