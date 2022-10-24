package web.service.impl;

import db.entity.Event;
import db.entity.dto.EventDTO;
import db.repository.EventRepository;
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
    public void updateEventInfo(Event event) {
        eventRepository.updateEvent(event);
    }

    @Override
    public Event findEvent(Event event) {
        return eventRepository.getEvent(event);
    }

    @Override
    public List<EventDTO> getEventDTOList() {
        return eventRepository.getEventDTOList();
    }
}