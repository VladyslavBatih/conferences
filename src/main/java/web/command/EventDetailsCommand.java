package web.command;

import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import web.service.EventService;
import web.service.ReportService;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventDetailsCommand extends Command {

    private final Logger LOGGER = Logger.getLogger(EventDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("EventDetailsCommand: starts working");
        LOGGER.trace("Event DTO id: " + request.getParameter("eventId"));

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(Integer.parseInt(request.getParameter("eventId")));

        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        List<ReportDTO> reportDTOList = reportService.getReportDTOList(eventDTO);
        LOGGER.trace("Report DTO list size: " + reportDTOList.size());

        request.setAttribute("reportDTOList", reportDTOList);
        request.setAttribute("eventDTO", eventDTO);

        LOGGER.info("Command finishes work");
        return "/WEB-INF/jsp/moderator/event_details.jsp";
    }
}