package util;

public final class DBConstant {

    public static final String SQL_INSERT_USER =
            "INSERT INTO users(login, password, firstname, lastname, role_id) VALUES (?,?,?,?,?)";

    public static final String SQL_UPDATE_USER = "UPDATE users SET firstname=?, lastname=? WHERE id_user=?";

    public static final String SQL_GET_USER_FROM_TABLE = "SELECT * FROM users";

    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=(?)";

    private DBConstant() {
    }
}