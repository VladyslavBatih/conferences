package web.listener;

import db.DBManager;
import db.dao.EventDAORepository;
import db.dao.ReportDAORepository;
import db.dao.UserDAORepository;
import db.repository.EventRepository;
import db.repository.ReportRepository;
import db.repository.UserRepository;
import db.repository.impl.EventRepositoryImpl;
import db.repository.impl.ReportRepositoryImpl;
import db.repository.impl.UserRepositoryImpl;
import util.Constant;
import util.Util;
import web.command.Command;
import web.service.EventService;
import web.service.ReportService;
import web.service.UserService;
import web.service.impl.EventServiceImpl;
import web.service.impl.ReportServiceImpl;
import web.service.impl.UserServiceImpl;
import web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Command.setContext(servletContext);

        DBManager dbManager = DBManager.getInstance();

        EventService eventService = getEventService(dbManager);
        ReportService reportService = getReportService(dbManager);
        UserService userService = getUserService(dbManager);

        Util util = new Util();
        Validator validator = new Validator(util);

        servletContext.setAttribute(Constant.VALIDATOR, validator);
        servletContext.setAttribute(Constant.UTIL, util);
        servletContext.setAttribute(Constant.EVENT_SERVICE, eventService);
        servletContext.setAttribute(Constant.REPORT_SERVICE, reportService);
        servletContext.setAttribute(Constant.USER_SERVICE, userService);
    }

    private EventService getEventService(DBManager dbManager) {
        EventDAORepository eventDAORepository = new EventDAORepository(dbManager);
        EventRepository eventRepository = new EventRepositoryImpl(eventDAORepository, dbManager);
        return new EventServiceImpl(eventRepository);
    }

    private ReportService getReportService(DBManager dbManager) {
        ReportDAORepository reportDAORepository = new ReportDAORepository(dbManager);
        ReportRepository reportRepository = new ReportRepositoryImpl(reportDAORepository, dbManager);
        return new ReportServiceImpl(reportRepository);
    }

    private UserService getUserService(DBManager dbManager) {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        UserRepository userRepository = new UserRepositoryImpl(userDAORepository, dbManager);
        return new UserServiceImpl(userRepository);
    }
}