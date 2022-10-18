package util;

public final class Path {


    public static final String PAGE_MODERATOR_EVENT_LIST = "/WEB-INF/jsp/moderator/event_list.jsp";
    public static final String PAGE_MODERATOR_EVENT_DETAILS = "/WEB-INF/jsp/moderator/event_details.jsp";

    public static final String PAGE_SPEAKER_EVENT_LIST = "/WEB-INF/jsp/speaker/event_list.jsp";
    public static final String PAGE_SPEAKER_EVENT_DETAILS = "/WEB-INF/jsp/speaker/event_details.jsp";

    public static final String PAGE_USER_EVENT_LIST = "/WEB-INF/jsp/user/event_list.jsp";
    public static final String PAGE_USER_EVENT_DETAILS = "/WEB-INF/jsp/user/event_details.jsp";

    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/common/settings.jsp"; // TODO implement jsp file
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/common/error_page.jsp";
    public static final String PAGE_LOGIN ="/index.jsp" ;
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/out-of-control/registration.jsp"; // TODO implement jsp file

    public static final String COMMAND_MODERATOR_PANEL = "/controller?command=moderatorPanel";
    public static final String COMMAND_SPEAKER_PANEL = "/controller?command=speakerPanel";
    public static final String COMMAND_USER_PANEL = "/controller?command=userPanel";

    // TODO implement commands
    public static final String COMMAND_CLIENT_ORDER_LIST ="/controller?command=orderClientList";
    public static final String COMMAND_MANAGER_CHECK_CARS = "/controller?command=checkCar" ;
    public static final String COMMAND_SETTINGS ="/controller?command=viewSettings" ;
    public static final String COMMAND_LOGIN = "/controller?command=login";

    private Path() {
    }
}