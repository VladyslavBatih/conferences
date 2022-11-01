package web.command;

import db.entity.dto.EventDTO;
import exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddReportViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(Integer.parseInt(request.getParameter("eventId")));
        request.setAttribute("eventDTO", eventDTO);
        return "/WEB-INF/jsp/moderator/add_report.jsp";
    }
}