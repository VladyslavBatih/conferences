package web.command;

import db.entity.Report;
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
import java.util.Map;

public class UpdateReportCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(UpdateReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("UpdateReportCommand: starts working");

        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String topic = request.getParameter("reportTopic");
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        ReportBean reportBean = new ReportBean();
        reportBean.setTopic(topic);
        reportBean.setEventId(eventId);
        LOGGER.info("ReportBean:" + reportBean);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(reportBean);

        String forward;
        if (errors.isEmpty()) {
            Report report = new Report();
            report.setId(reportId);
            report.setEventId(eventId);
            report.setTopic(topic);
            reportService.updateReportInfo(report);
            forward = "controller?command=eventDetails&eventId=" + eventId;
        } else {
            LOGGER.error("Errors: " + errors.size());
            request.setAttribute("errors", errors);

            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventId);

            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(reportId);
            reportDTO.setTopic(topic);

            request.setAttribute("eventDTO", eventDTO);
            request.setAttribute("reportDTO", reportDTO);
            forward = "/WEB-INF/jsp/moderator/update_report.jsp";
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}
