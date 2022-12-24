package web.command;

import db.entity.Event;
import db.entity.dto.EventDTO;
import exception.AppException;
import util.Constant;
import web.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEventViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        Event event = new Event();
        event.setName(request.getParameter("eventName"));

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);
        Event currentEvent = eventService.findEvent(event);

        EventDTO eventDTO = new EventDTO();
        eventDTO.setName(currentEvent.getName());
        eventDTO.setDate(currentEvent.getDate());
        eventDTO.setPlace(currentEvent.getPlace());
        eventDTO.setTime(currentEvent.getTime());

        request.setAttribute("eventDTO", eventDTO);

        return "/WEB-INF/jsp/moderator/update_event.jsp";
    }
}