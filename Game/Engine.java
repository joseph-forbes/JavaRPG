package Game;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import Commands.*;
import Player.Player;
import WorldMap.WorldMap;

public class Engine {
    private Map<String, Command> commands = new HashMap<>();
    Player player;
    WorldMap worldMap;

    Scanner keyboard = new Scanner(System.in);


    public Engine() {
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
            command.execute(this, otherParts);
        } else {
            System.out.println("Unknown command. Type \"help\" for a list of commands.");
        }
    }

    void start() {
        System.out.print("Name your character: ");
        player = new Player(keyboard.nextLine());
        worldMap = new WorldMap(this);

        System.out.println("This is the beginning of the adventure of " + player.getName() + '.');

        System.out.println();
        System.out.println("On your initial look around: ");
        executeCommand("look around"); // initially look at everything around you so you have a vibe of the world
        System.out.println();

        System.out.println("Type \"help\" for a list of commands.");

        boolean gameRunning = true;
        while(gameRunning) {
            update();
        }
    }

    void update() {
        System.out.print("What do you want to do next? ");
        String input = keyboard.nextLine();
        System.out.println();
        executeCommand(input);
        System.out.println();

        System.out.println("The adventure of " + player.getName() + " continues.");
        
    }
    public WorldMap getMap() {
        return worldMap;
    }
    public Player getPlayer() {
        return player;
    }
    public void end() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
