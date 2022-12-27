package web.command;

import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import web.bean.ReportBean;
import web.service.ReportService;
import web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddReportCommand extends Command {

    private final Logger LOGGER = Logger.getLogger(AddReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("AddReportCommand: starts working");

        String topic = request.getParameter("topic");
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(Integer.parseInt(request.getParameter("eventId")));
        request.setAttribute("eventDTO", eventDTO);

        ReportBean reportBean = new ReportBean();
        reportBean.setTopic(topic);
        reportBean.setEventId(eventDTO.getId());

        LOGGER.debug("Event id: " + eventDTO.getId());

        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        reportBean.setReport(reportService.findReport(reportBean));
        LOGGER.debug("ReportBean: " + reportBean);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(reportBean);

        LOGGER.debug("Errors: " + errors.size());

        String forward;
        if (errors.isEmpty()) {
            reportService.addReport(reportBean);
            List<ReportDTO> reportDTOList = reportService.getReportDTOList(eventDTO);
            request.setAttribute("reportDTOList", reportDTOList);
            forward = "/WEB-INF/jsp/moderator/event_details.jsp";
        } else {
            request.setAttribute("errors", errors);
            forward = "/WEB-INF/jsp/moderator/add_report.jsp";
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}