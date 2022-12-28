package web.command;

import db.Role;
import db.entity.User;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EventDetailsCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(EventDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("EventDetailsCommand: starts working");
        LOGGER.trace("EventDTO id: " + request.getParameter("eventId"));

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(Integer.parseInt(request.getParameter("eventId")));

        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        List<ReportDTO> reportDTOList = reportService.getReportDTOList(eventDTO);
        LOGGER.trace("Report DTO list size: " + reportDTOList.size());

        request.setAttribute("reportDTOList", reportDTOList);
        request.setAttribute("eventDTO", eventDTO);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role userRole = Role.getRole(user);

        String forward = Path.PAGE_LOGIN;
        if (userRole == Role.MODERATOR) {
            forward = "/WEB-INF/jsp/moderator/event_details.jsp";
        }
        if (userRole == Role.SPEAKER) {
            forward = "/WEB-INF/jsp/speaker/event_details.jsp";
        }
        if (userRole == Role.USER) {
            forward = "/WEB-INF/jsp/user/event_details.jsp";
        }

        LOGGER.info("Command finishes work");
        return forward;
    }
}