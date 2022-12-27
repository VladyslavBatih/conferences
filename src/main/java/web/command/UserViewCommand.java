package web.command;

import db.entity.dto.EventDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserViewCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(UserViewCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("UserViewCommand: starts working");

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);
        List<EventDTO> eventDTOList = eventService.getEventDTOList();
        request.setAttribute("eventDTOList", eventDTOList);

        LOGGER.info("Command finishes work");
        return Path.PAGE_USER_EVENT_LIST;
    }
}