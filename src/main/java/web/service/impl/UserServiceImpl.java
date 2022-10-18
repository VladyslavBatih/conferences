package web.service.impl;

import db.Role;
import db.entity.User;
import db.entity.dto.UserDTO;
import db.repository.UserRepository;
import web.bean.AuthBean;
import web.bean.RegistrationBean;
import web.bean.SettingBean;
import web.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUserInfo(SettingBean settingBean) {
        userRepository.updateUser(getEntity(settingBean));
    }

    @Override
    public void createUser(RegistrationBean registrationBean) {
        userRepository.createUser(getEntity(registrationBean, Role.USER));
    }

    @Override
    public void createSpeaker(RegistrationBean registrationBean) {
        userRepository.createUser(getEntity(registrationBean, Role.SPEAKER));
    }

    @Override
    public List<UserDTO> getUserDTOList() {
        return userRepository.getUserDTOList();
    }

    @Override
    public User findUser(AuthBean authBean) {
        return userRepository.getUser(getEntity(authBean));
    }

    @Override
    public User findUser(RegistrationBean registrationBean) {
        return userRepository.getUser(getEntity(registrationBean));
    }

    @Override
    public User findUser(User user) {
        return userRepository.getUser(user);
    }

    private User getEntity(SettingBean settingBean) {
        User user = new User();
        user.setId(settingBean.getId());
        user.setFirstName(settingBean.getFirstName());
        user.setLastName(settingBean.getLastName());
        return user;
    }

    private User getEntity(RegistrationBean registrationBean) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        return user;
    }

    private User getEntity(RegistrationBean registrationBean, Role role) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setPassword(registrationBean.getPassword());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        user.setRoleId(role.getNumber());
        return user;
    }

    private User getEntity(AuthBean authBean) {
        User user = new User();
        user.setLogin(authBean.getLogin());
        user.setPassword(authBean.getPassword()); // TODO delete ???
        return user;
    }
}