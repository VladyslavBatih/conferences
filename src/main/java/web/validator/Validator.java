package web.validator;

import db.entity.User;
import util.Util;
import web.bean.AuthBean;
import web.bean.RegistrationBean;
import web.bean.SettingBean;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Validator {

    private final Util util;

    private final String PREFIX = "valid_";

    public Validator(Util util) {
        this.util = util;
    }

    public Map<String, String> validate(SettingBean settingBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "settings";
        if (Objects.isNull(settingBean.getFirstName()) || settingBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + postPrefix, "firstName"));
        }
        if (Objects.isNull(settingBean.getLastName()) || settingBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + postPrefix, "lastName"));
        }
        return errors;
    }

    public Map<String, String> validate(RegistrationBean registrationBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "moderator"; // TODO "admin"
        if (Objects.isNull(registrationBean.getLogin()) || registrationBean.getLogin().isEmpty()) {
            errors.put("login", util.getTranslate(locale, PREFIX + postPrefix, "login"));
        } else {
            if (Objects.nonNull(registrationBean.getUser())) {
                errors.put("login", util.getTranslate(locale, PREFIX + postPrefix, "exist"));
            }
        }
        if (Objects.isNull(registrationBean.getPassword()) || registrationBean.getPassword().isEmpty()) {
            errors.put("password", util.getTranslate(locale, PREFIX + postPrefix, "password"));
        } else {
            if (Objects.isNull(registrationBean.getConfirm()) || registrationBean.getConfirm().isEmpty()) {
                errors.put("confirm", "Fill confirm field");
            } else {
                if (!registrationBean.getPassword().equals(registrationBean.getConfirm())) {
                    errors.put("confirm", util.getTranslate(locale, PREFIX + postPrefix, "mismatch"));
                }
            }
        }
        if (Objects.isNull(registrationBean.getFirstName()) || registrationBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + postPrefix, "firstName"));
        }
        if (Objects.isNull(registrationBean.getLastName()) || registrationBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + postPrefix, "lastName"));
        }
        return errors;
    }

    public Map<String, String> validate(AuthBean authBean) {
        Map<String, String> errors = new HashMap<>();
        User user;
        if (Objects.isNull(authBean.getLogin()) || authBean.getLogin().isEmpty()) {
            errors.put("login", "Fill login field");
        } else {
            if (Objects.nonNull(authBean.getUser())) {
                if (!authBean.getPassword().equals(authBean.getUser().getPassword())) {
                    errors.put("password", "The password is incorrect");
                }
            } else {
                errors.put("login", "User does not exist");
            }
        }
        if (Objects.isNull(authBean.getPassword()) || authBean.getPassword().isEmpty()) {
            errors.put("password", "Fill password field");
        }
        return errors;
    }
}