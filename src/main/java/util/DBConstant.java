package util;

public final class DBConstant {

    public static final String SQL_INSERT_USER =
            "INSERT INTO users(login, password, firstname, lastname, role_id) VALUES (?,?,?,?,?)";

    public static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET firstname=?, lastname=? WHERE id=?";

    public static final String SQL_GET_USER_FROM_TABLE = "SELECT * FROM users";

    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=(?)";

    public static final String SQL_GET_ALL_USER_BY_REPORT = "SELECT * FROM reports_users WHERE report_id = ";

    public static final String SQL_GET_EVENT_BY_NAME = "SELECT * FROM events WHERE name=?";

    public static final String SQL_INSERT_EVENT = "INSERT INTO events(name, place, date, time) VALUES (?,?,?,?)";

    public static final String SQL_UPDATE_EVENT_BY_ID = "UPDATE events SET name=?, place=?, date=?, time=? WHERE id=?";

    public static final String SQL_DELETE_EVENT_BY_ID = "DELETE FROM events WHERE id=?";

    public static final String SQL_GET_ALL_EVENT = "SELECT * FROM events";

    public static final String SQL_GET_ALL_REPORT_BY_EVENT_AND_TOPIC =
            "SELECT * FROM reports WHERE event_id=? and topic=?";

    public static final String SQL_INSERT_REPORT = "INSERT INTO reports(topic, event_id, speaker_id) VALUES (?,?,?)";

    public static final String SQL_UPDATE_REPORT_BY_ID = "UPDATE reports SET topic=? WHERE id=?";

    public static final String SQL_DELETE_REPORT_BY_ID = "DELETE FROM reports WHERE id=?";

    public static final String SQL_GET_REPORT_BY_SPEAKER =
            "SELECT reports.id, reports.topic, users.firstname, users.lastname " +
            "FROM reports " +
            "INNER JOIN users ON reports.speaker_id = users.id";

    public static final String SQL_GET_REPORT_BY_SPEAKER_AND_EVENT =
            "SELECT reports.id, reports.topic, users.firstname, users.lastname " +
            "FROM reports " +
            "INNER JOIN users ON reports.speaker_id = users.id " +
            "WHERE reports.event_id = ";

    private DBConstant() {
    }
}