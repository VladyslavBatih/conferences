package web.command;

import db.entity.Event;
import db.entity.dto.EventDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.bean.EventBean;
import web.service.EventService;
import web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateEventCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(UpdateEventCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("UpdateEventCommand: starts working");

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);

        String name = request.getParameter("name");
        String place = request.getParameter("place");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        EventBean eventBean = new EventBean(name, place, date, time);
        LOGGER.debug(eventBean);

        int id = eventService.findEvent(eventBean).getId();

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(eventBean);

        LOGGER.debug("Errors: " + errors.size());
        LOGGER.debug("EventBean: " + eventBean);

        String forward;
        if (errors.isEmpty()) {
            Event event = new Event(
                    eventBean.getName(),
                    eventBean.getPlace(),
                    eventBean.getDate(),
                    eventBean.getTime());
            event.setId(id);
            eventService.updateEventInfo(event);
            forward = Path.COMMAND_MODERATOR_PANEL;
        } else {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setName(name);
            eventDTO.setPlace(place);
            eventDTO.setDate(date);
            eventDTO.setTime(time);

            request.setAttribute("eventDTO", eventDTO);
            request.setAttribute("errors", errors);
            forward = "/WEB-INF/jsp/moderator/update_event.jsp";
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}