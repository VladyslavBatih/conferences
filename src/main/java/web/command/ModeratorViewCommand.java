package web.command;

import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import db.entity.dto.UserDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.EventService;
import web.service.ReportService;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModeratorViewCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ModeratorViewCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("ModeratorViewCommand: starts working");

        EventService eventService = (EventService) servletContext.getAttribute(Constant.EVENT_SERVICE);
        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE);

        List<EventDTO> eventDTOList = eventService.getEventDTOList();
        List<ReportDTO> reportDTOList = reportService.getReportDTOList();
        List<UserDTO> userDTOList = userService.getUserDTOList();

        request.setAttribute("eventDTOList", eventDTOList);
        request.setAttribute("reportDTOList", reportDTOList);
        request.setAttribute("userDTOList", userDTOList);

        LOGGER.info("Command finishes work");
        return Path.PAGE_MODERATOR_EVENT_LIST;
    }
}