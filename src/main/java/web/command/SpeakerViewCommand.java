package web.command;

import db.entity.dto.UserDTO;
import exception.AppException;
import util.Constant;
import util.Path;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpeakerViewCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE);
        List<UserDTO> userDTOList = userService.getUserDTOList();
        request.setAttribute("userDTOList", userDTOList);

        return Path.PAGE_SPEAKER_EVENT_LIST;
    }
}
