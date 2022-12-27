package util;

public final class Path {

    public static final String PAGE_MODERATOR_EVENT_LIST = "/WEB-INF/jsp/moderator/event_list.jsp";

    public static final String PAGE_SPEAKER_EVENT_LIST = "/WEB-INF/jsp/speaker/event_list.jsp";

    public static final String PAGE_USER_EVENT_LIST = "/WEB-INF/jsp/user/event_list.jsp";

    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/common/error_page.jsp";
    public static final String PAGE_LOGIN ="/index.jsp" ;

    public static final String COMMAND_MODERATOR_PANEL = "/controller?command=moderatorPanel";
    public static final String COMMAND_SPEAKER_PANEL = "/controller?command=speakerPanel";
    public static final String COMMAND_USER_PANEL = "/controller?command=userPanel";

    private Path() {
    }
}