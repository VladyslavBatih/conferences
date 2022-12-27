package web.command;

import db.entity.Report;
import db.entity.dto.EventDTO;
import db.entity.dto.ReportDTO;
import exception.AppException;
import util.Constant;
import web.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateReportViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        int eventId = Integer.parseInt(request.getParameter("eventId"));

        Report report = new Report();
        report.setTopic(request.getParameter("reportTopic"));
        report.setEventId(eventId);

        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        Report currentReport = reportService.findReport(report);

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setTopic(currentReport.getTopic());
        reportDTO.setId(currentReport.getId());

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);

        request.setAttribute("reportDTO", reportDTO);
        request.setAttribute("eventDTO", eventDTO);

        return "/WEB-INF/jsp/moderator/update_report.jsp";
    }
}