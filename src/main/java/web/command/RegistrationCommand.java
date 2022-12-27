package web.command;

import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import web.bean.RegistrationBean;
import web.service.UserService;
import web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RegistrationCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("RegistrationCommand: starts working");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int role = request.getParameter("role").equals("speaker") ? 1 : 2;
        LOGGER.debug("role --> " + role);

        RegistrationBean registrationBean = new RegistrationBean(login, password, confirm, firstname, lastname, role);

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE);

        registrationBean.setUser(userService.findUser(registrationBean));

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(registrationBean);

        LOGGER.debug("Errors: " + errors.size());
        LOGGER.debug("RegistrationBean: " + registrationBean);
        String forward;
        if (errors.isEmpty()) {
            userService.addUser(registrationBean);
            forward = "/controller?command=login";
        } else {
            request.setAttribute("login", login);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("errors", errors);
            forward = "/WEB-INF/jsp/common/registration.jsp";
        }
        LOGGER.info("Command finishes work");
        return forward;
    }
}