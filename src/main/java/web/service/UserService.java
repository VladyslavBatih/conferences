package web.service;

import db.entity.User;
import db.entity.dto.UserDTO;
import web.bean.AuthBean;
import web.bean.RegistrationBean;
import web.bean.SettingBean;

import java.util.List;

public interface UserService {

    void updateUserInfo(SettingBean settingBean);

    void addUser(RegistrationBean registrationBean);

    List<UserDTO> getUserDTOList();

    User findUser(AuthBean authBean);

    User findUser(RegistrationBean registrationBean);

    User findUser(User user);
}