package web.command;

import exception.AppException;
import org.apache.log4j.Logger;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand extends Command {

    private final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("No command: starts working");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOGGER.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOGGER.info("Command finishes work");
        return Path.PAGE_ERROR_PAGE;
    }
}