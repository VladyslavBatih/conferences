package web.command;

import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import web.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReportCommand extends Command {

    Logger LOGGER = Logger.getLogger(DeleteReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("DeleteReportCommand: starts working");

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        int eventID = Integer.parseInt(request.getParameter("eventId"));
        ReportService reportService = (ReportService) servletContext.getAttribute(Constant.REPORT_SERVICE);
        reportService.removeReport(reportId);

        LOGGER.trace("Event was successfully removed with id: " + reportId);

        return "controller?command=eventDetails&eventId=" + eventID;
    }
}