package util;

public final class LoggerUtil {

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Can't obtain data source.";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Can't obtain connection.";
    public static final String ERR_FAIL_TRANSACTION = "Cannot do transaction.";
    public static final Object ERR_CANNOT_CONVERT_STRING_TO_TIMESTAMP = "Cannot convert string to Timestamp type.";

    public static final String COMMAND_START = "Controller starts.";
    public static final String COMMAND_OBTAIN = "Obtained command --> ";
    public static final String COMMAND_FORWARD = "Forward address --> ";
    public static final String COMMAND_GO_FORWARD = "Go to forward address --> ";
    public static final String COMMAND_COMMAND_PARAMETER = "Request parameter: command --> ";

    private LoggerUtil() {
    }
}