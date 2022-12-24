package web.service;

import db.entity.Event;
import db.entity.dto.EventDTO;
import web.bean.EventBean;

import java.util.List;

public interface EventService {

    void addEvent(Event event);

    void addEvent(EventBean eventBean);

    void updateEventInfo(Event event);

    Event findEvent(Event event);

    Event findEvent(EventBean eventBean);

    void removeEvent(int id);

    List<EventDTO> getEventDTOList();
}