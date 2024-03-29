package web.command;

import db.entity.Event;
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

public class AddEventCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AddEventCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("AddEventCommand: starts working");

        String name = request.getParameter("name");
        String place = request.getParameter("place");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        EventBean eventBean = new EventBean(name, place, date, time);
        LOGGER.info("EventBean: " + eventBean);

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);

        Event event = eventService.findEvent(eventBean);
        eventBean.setEvent(eventService.findEvent(eventBean));
        LOGGER.info("Event: " + event);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(eventBean);

        String forward;
        if (errors.isEmpty()) {
            eventService.addEvent(eventBean);
            forward = Path.COMMAND_MODERATOR_PANEL;
        } else {
            LOGGER.error("Errors: " + errors.size());
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("place", place);
            request.setAttribute("date", date);
            request.setAttribute("time", time);
            forward = "/WEB-INF/jsp/moderator/add_event.jsp";
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}