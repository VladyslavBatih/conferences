package web.listener;

import db.DBManager;
import db.dao.UserDAORepository;
import db.repository.UserRepository;
import db.repository.impl.UserRepositoryImpl;
import util.Constant;
import util.Util;
import web.command.Command;
import web.service.UserService;
import web.service.impl.UserServiceImpl;

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
        UserService userService = getUserService(dbManager);
//        BillService billService = getBillService(dbManager);
//        CarService carService = getCarService(dbManager);
//        OrderService orderService = getOrderService(dbManager);

        Util util = new Util();
//        Validator validator = new Validator(util);


//        servletContext.setAttribute(Constant.VALIDATOR, validator);
        servletContext.setAttribute(Constant.UTIL, util);
        servletContext.setAttribute(Constant.USER_SERVICE_MANAGER, userService);
//        servletContext.setAttribute(Constant.BILL_SERVICE_MANAGER,billService);
//        servletContext.setAttribute(Constant.CAR_SERVICE_MANAGER,carService);
//        servletContext.setAttribute(Constant.ORDER_SERVICE_MANAGER, orderService);
    }

    private UserService getUserService(DBManager dbManager) {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        UserRepository userRepository = new UserRepositoryImpl(userDAORepository, dbManager);
        return new UserServiceImpl(userRepository);
    }
}