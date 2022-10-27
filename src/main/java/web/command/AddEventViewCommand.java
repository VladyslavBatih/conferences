package web.command;

import exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return "/WEB-INF/jsp/moderator/add_event.jsp";
    }
}