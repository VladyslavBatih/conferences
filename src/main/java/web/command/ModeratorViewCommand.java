package web.command;

import db.entity.dto.UserDTO;
import exception.AppException;
import org.apache.log4j.Logger;
import util.Constant;
import util.Path;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModeratorViewCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ModeratorViewCommand.class); // TODO ???

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOGGER.info("ModeratorViewCommand: starts working");

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
        List<UserDTO> userDTOList = userService.getUserDTOList();
        request.setAttribute("userDTOList", userDTOList);

//        List<UserDTO> userDTOList = userService.getUserDTO();
//        List<CarDTO> carDTOList = carService.getAllCarDTO();
//        List<Brand> brandList = carService.getBrandList();
//        List<Category> categoryList = carService.getCategoryList();
//        request.setAttribute("userDTOList", userDTOList);
//        request.setAttribute("carDTOList", carDTOList);
//        request.setAttribute("brandList", brandList);
//        request.setAttribute("categoryList", categoryList);
//        userDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
//        carDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        LOGGER.info("Command finishes work");
        return Path.PAGE_MODERATOR_EVENT_LIST;
    }
}