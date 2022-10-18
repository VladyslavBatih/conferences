package web.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LogInCommand());
        commands.put("moderatorPanel", new ModeratorViewCommand());
        commands.put("speakerPanel", new SpeakerViewCommand());
        commands.put("userPanel", new UserViewCommand());
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