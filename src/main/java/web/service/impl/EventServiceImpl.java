package web.service.impl;

import db.entity.Event;
import db.entity.dto.EventDTO;
import db.repository.EventRepository;
import web.bean.EventBean;
import web.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.createEvent(event);
    }

    @Override
    public void addEvent(EventBean eventBean) {
        eventRepository.createEvent(getEntity(eventBean));
    }

    @Override
    public void updateEventInfo(Event event) {
        eventRepository.updateEvent(event);
    }

    @Override
    public Event findEvent(Event event) {
        return eventRepository.getEvent(event);
    }

    @Override
    public Event findEvent(EventBean eventBean) {
        return eventRepository.getEvent(getEntity(eventBean));
    }

    @Override
    public List<EventDTO> getEventDTOList() {
        return eventRepository.getEventDTOList();
    }

    private Event getEntity(EventBean eventBean) {
        return new Event(
                eventBean.getName(),
                eventBean.getPlace(),
                eventBean.getDate(),
                eventBean.getTime());
    }
}