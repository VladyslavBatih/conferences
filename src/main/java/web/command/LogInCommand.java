package web.command;

import db.Role;
import db.entity.User;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.bean.AuthBean;
import web.service.UserService;
import web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LogInCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(LogInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("LogInCommand: starts working");

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        request.setAttribute("login", login);
        LOGGER.info("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        LOGGER.info("Request parameter: password --> " + password);

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE);

        AuthBean authBean = new AuthBean(login, password);
        User user = userService.findUser(authBean); // TODO if user == null
        authBean.setUser(user);

        session.setAttribute(Constant.USER, user);
        LOGGER.info("Set the session attribute: user --> " + user);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(authBean);
        request.setAttribute("errors", errors);

        String forward = Path.PAGE_LOGIN;

        if (!errors.isEmpty()) {
            request.setAttribute("login", login);
            return forward;
        }

        Role userRole = Role.getRole(user);
        session.setAttribute(Constant.USER_ROLE, userRole);
        LOGGER.trace("Set the session attribute: userRole --> " + userRole);

        if (userRole == Role.MODERATOR) {
            forward = Path.COMMAND_MODERATOR_PANEL;
        }
        if (userRole == Role.SPEAKER) {
            forward = Path.COMMAND_SPEAKER_PANEL;
        }
        if (userRole == Role.USER) {
            forward = Path.COMMAND_USER_PANEL;
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}