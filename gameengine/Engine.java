package gameengine;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import player.Player;
import worldmap.*;
import commands.*;
import util.enums.Stats;
import util.returnsutil.ManReturn;

public class Engine {
    private Map<String, Command> commands = new HashMap<>();
    Player player;
    WorldMap worldMap;
    List<ManReturn> manList = new ArrayList<>();
    public boolean isNSFW;

    Scanner keyboard = new Scanner(System.in);


    public Engine() {
        isNSFW = false;
        registerCommands();
    }

    private void registerCommands() {
        registerCommand("die", new Die());
        registerCommand("help", new Help());
        registerCommand("hit", new Hit());
        registerCommand("inventory", new commands.Inventory());
        registerCommand("look", new Look());
        registerCommand("move", new Move());
        registerCommand("pickup", new Pickup());
        registerCommand("use", new Use());
        registerCommand("equip", new Equip());
        registerCommand("unequip", new Unequip());
        registerCommand("nsfw", new WhyAreYouLikeThis());
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
            render("Unknown command. Type \"help\" for a list of commands.");
        }
    }

    public void start() {
        render("Name your character: ");
        player = new Player(keyboard.nextLine());
        worldMap = new WorldMap(this);

        render("This is the beginning of the adventure of " + player.getName() + '.');

        render("\nOn your initial look around: ");
        executeCommand("look around"); // initially look at everything around you so you have a vibe of the world

        render("\nType \"help\" for a list of commands.");

        boolean gameRunning = true;
        while(gameRunning) {
            update();
        }
    }

    public void update() {
        System.out.print("What do you want to do next? ");
        String input = keyboard.nextLine();
        render();
        executeCommand(input);

        worldMap.update();
        player.update();

        if(player.isDead()) {
            render("You died.");
            end();
        } else {
            render("\nThe adventure of " + player.getName() + " continues. HP: " + player.getStat(Stats.HP));
        }
        
    }
    
    
    public WorldMap getMap() {
        return worldMap;
    }
    public Player getPlayer() {
        return player;
    }
    public void end() {
        render("Thanks for playing!");
        System.exit(0);
    }
    public List<ManReturn> getManList() {
        return manList;
    }

    public void render(String text) {
        System.out.println(text);
    }
    public void render() {
        System.out.println();
    }
}
