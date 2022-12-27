package db.entity.dto;

import java.util.Objects;

public class UserDTO {

    private int id;
    private String login;
    private String firstName;
    private String lastName;

    public UserDTO() {
    }

    public UserDTO(int id, String login, String firstName, String lastName) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return login.equals(userDTO.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "UserDTO {" +
                "login = '" + login + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }
}