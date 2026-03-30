package Game;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Commands.*;
import Player.Player;
import WorldMap.WorldMap;
import util.Returns.ManReturn;

public class Engine {
    private Map<String, Command> commands = new HashMap<>();
    Player player;
    WorldMap worldMap;
    List<ManReturn> manList = new ArrayList<>();

    Scanner keyboard = new Scanner(System.in);


    public Engine() {
        registerCommands();
    }

    private void registerCommands() {
        registerCommand("die", new Die());
        registerCommand("help", new Help());
        registerCommand("hit", new Hit());
        registerCommand("look", new Look());
        registerCommand("move", new Move());
    }
    private void registerCommand(String string, Command command) {
        commands.put(string, command);
        manList.add(new ManReturn(string, command.getMan())); // purely stored for the Help command
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

        worldMap.update();
        player.update();

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
    public List<ManReturn> getManList() {
        return manList;
    }
}
