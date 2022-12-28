package web.command;

import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEventCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteEventCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("DeleteEventCommand: starts working");

        int id = Integer.parseInt(request.getParameter("eventId"));
        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);
        eventService.removeEvent(id);

        LOGGER.trace("Event was successfully removed with id: " + id);

        return Path.COMMAND_MODERATOR_PANEL;
    }
}