package web.command;

import db.entity.Event;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(AddEventCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("ModeratorViewCommand: starts working");

        Event event = new Event(
                request.getParameter("name"),
                request.getParameter("place"),
                request.getParameter("date"),
                request.getParameter("time"));

        LOGGER.trace("Add new event --> " + event);

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);
        eventService.addEvent(event);

        LOGGER.info("Command finishes work");
        return "/WEB-INF/jsp/user/add_event.jsp";
    }
}