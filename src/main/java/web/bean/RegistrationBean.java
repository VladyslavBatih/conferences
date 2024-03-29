package web.bean;

import db.entity.User;

public class RegistrationBean {

    private String login;
    private String password;
    private String confirm;
    private String firstName;
    private String lastName;
    private int role;
    private User user;

    public RegistrationBean() {
    }

    public RegistrationBean(String login, String password, String confirm, String firstName, String lastName, int role) {
        this.login = login;
        this.password = password;
        this.confirm = confirm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RegistrationBean {" +
                "login = '" + login + '\'' +
                ", password = '" + password + '\'' +
                ", confirm = '" + confirm + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", role = " + role +
                '}';
    }
}