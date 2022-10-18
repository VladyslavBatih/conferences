package web;

import db.entity.User;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.LoggerUtil;
import util.Path;
import web.command.Command;
import web.command.CommandContainer;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.debug(LoggerUtil.COMMAND_START);

        Command command = getCommand(request);
        LOGGER.trace(LoggerUtil.COMMAND_OBTAIN + command);

        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOGGER.trace(LoggerUtil.COMMAND_FORWARD + forward);
        LOGGER.debug(LoggerUtil.COMMAND_GO_FORWARD + forward);

        request.getRequestDispatcher(forward).forward(request, response);
    }

    private Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        LOGGER.trace(LoggerUtil.COMMAND_COMMAND_PARAMETER + commandName);
        Command command = CommandContainer.getCommand(commandName);
        UserService userService = (UserService) getServletContext().getAttribute(Constant.USER_SERVICE_MANAGER);
        User user = (User) request.getSession().getAttribute(Constant.USER);
        if (Objects.nonNull(user)) {
            user = userService.findUser(user);
            request.getSession().setAttribute(Constant.USER, user);
        }
        return command;
    }
}