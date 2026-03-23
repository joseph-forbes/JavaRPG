package Game;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import Commands.*;

public class Engine {
    private Map<String, Command> commands = new HashMap<>();
    Adventure game;


    public Engine(Adventure adventure) {
        game = adventure;
        registerCommands();
    }

    private void registerCommands() {
        commands.put("help", new Help());
        commands.put("move", new Move());
        commands.put("hit", new Hit());
        commands.put("look", new Look()); // takes in name for an easter egg
        commands.put("die", new Die());
    }

    public void executeCommand(String input) {

        String[] parts = input.split(" ");
        String commandName = parts[0].toLowerCase();
        String[] otherParts = Arrays.copyOfRange(parts, 1, parts.length);

        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(game, otherParts);
        } else {
            System.out.println("Unknown command. Type \"help\" for a list of commands.");
        }
    }


}
