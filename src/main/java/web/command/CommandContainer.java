package web.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LogInCommand());
        commands.put("loginView", new LogInViewCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("registrationView", new RegistrationViewCommand());
        commands.put("moderatorPanel", new ModeratorViewCommand());
        commands.put("speakerPanel", new SpeakerViewCommand());
        commands.put("userPanel", new UserViewCommand());
        commands.put("addEvent", new AddEventCommand());
        commands.put("addEventView", new AddEventViewCommand());
        commands.put("eventDetailsView", new EventDetailsViewCommand());
        commands.put("eventDetails", new EventDetailsCommand());
        commands.put("addReportView", new AddReportViewCommand());
        commands.put("addReport", new AddReportCommand());
        commands.put("updateEventView", new UpdateEventViewCommand());
        commands.put("updateEvent", new UpdateEventCommand());
        commands.put("deleteEvent", new DeleteEventCommand());
        commands.put("noCommand", new NoCommand());
    }

    public static Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

    private CommandContainer(){
    }
}