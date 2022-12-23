package web.validator;

import util.Util;
import web.bean.AuthBean;
import web.bean.EventBean;
import web.bean.RegistrationBean;
import web.bean.ReportBean;
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

    public Map<String, String> validate(RegistrationBean registrationBean) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(registrationBean.getLogin()) || registrationBean.getLogin().isEmpty()) {
            errors.put("login", "Fill in login field");
        } else {
            if (Objects.nonNull(registrationBean.getUser())) {
                errors.put("login",  "User with this login already exist");
            }
        }
        if (Objects.isNull(registrationBean.getPassword()) || registrationBean.getPassword().isEmpty()) {
            errors.put("password",  "Put your password");
        } else {
            if (Objects.isNull(registrationBean.getConfirm()) || registrationBean.getConfirm().isEmpty()) {
                errors.put("confirm", "Confirm your password");
            } else {
                if (!registrationBean.getPassword().equals(registrationBean.getConfirm())) {
                    errors.put("confirm", "Password mismatch");
                }
            }
        }
        if (Objects.isNull(registrationBean.getFirstName()) || registrationBean.getFirstName().isEmpty()) {
            errors.put("firstName", "Fill in your firstname");
        }
        if (Objects.isNull(registrationBean.getLastName()) || registrationBean.getLastName().isEmpty()) {
            errors.put("lastName", "Fill in your lastname");
        }
        return errors;
    }

    public Map<String, String> validate(EventBean eventBean) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(eventBean.getName()) || eventBean.getName().isEmpty()) {
            errors.put("name", "Fill in event name");
        } else {
            if (Objects.nonNull(eventBean.getEvent())) {
                errors.put("name",  "This event already exist");
            }
        }
        if (Objects.isNull(eventBean.getPlace()) || eventBean.getPlace().isEmpty()) {
            errors.put("place",  "Fill in event place");
        }
        if (Objects.isNull(eventBean.getDate()) || eventBean.getDate().isEmpty()) {
            errors.put("date", "Fill in event date");
        } else {
            if(!eventBean.getDate().matches("([12]\\d{3}.(0[1-9]|1[0-2]).(0[1-9]|[12]\\d|3[01]))")) {
                errors.put("date", "Date format: yyyy.mm.dd");
            }
        }
        if (Objects.isNull(eventBean.getTime()) || eventBean.getTime().isEmpty()) {
            errors.put("time", "Fill in event time");
        } else {
            if(!eventBean.getTime().matches("([01]?\\d|2[0-3]):[0-5]\\d")) {
                errors.put("time", "Time format: hh:mm");
            }
        }
        return errors;
    }

    public Map<String, String> validate(ReportBean reportBean) {
        Map<String, String> errors = new HashMap<>();
        if (Objects.isNull(reportBean.getTopic()) || reportBean.getTopic().isEmpty()) {
            errors.put("topic", "Fill in report topic");
        } else {
            if (Objects.nonNull(reportBean.getReport())) {
                errors.put("topic",  "Report with this topic already exist");
            }
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
        if (Objects.isNull(authBean.getLogin()) || authBean.getLogin().isEmpty()) {
            errors.put("login", "Fill in login field");
        } else {
            if (Objects.nonNull(authBean.getUser())) {
                if (!authBean.getPassword().equals(authBean.getUser().getPassword())) {
                    errors.put("password", "The password is incorrect");
                }
            } else {
                errors.put("login", "User with this login does not exist");
            }
        }
        if (Objects.isNull(authBean.getPassword()) || authBean.getPassword().isEmpty()) {
            errors.put("password", "Fill in password field");
        }
        return errors;
    }
}