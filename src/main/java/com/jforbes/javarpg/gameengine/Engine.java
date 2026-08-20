package com.jforbes.javarpg.gameengine;
import java.util.Map;
import java.util.Scanner;

import com.jforbes.javarpg.commands.*;
import com.jforbes.javarpg.player.Player;
import com.jforbes.javarpg.util.enums.Stats;
import com.jforbes.javarpg.util.render.*;
import com.jforbes.javarpg.util.returnsutil.ManReturn;
import com.jforbes.javarpg.worldmap.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private Map<String, Command> commands = new HashMap<>();
    private Player player;
    private World world;
    private List<ManReturn> manList = new ArrayList<>();
    public boolean isNSFW;
    private final GameOutput output;

    Scanner keyboard = new Scanner(System.in);


    public Engine() {
        this(new TerminalOutput());
    }
    public Engine(GameOutput output) {
        isNSFW = false;
        this.output = output;
        registerCommands();
    }

    private void registerCommands() {
        registerCommand("die", new Die());
        registerCommand("help", new Help());
        registerCommand("hit", new Hit());
        registerCommand("inventory", new SeeInventory());
        registerCommand("look", new Look());
        registerCommand("move", new Move());
        registerCommand("pickup", new Pickup());
        registerCommand("use", new Use());
        registerCommand("interact", new Interact());
        registerCommand("equip", new Equip());
        registerCommand("unequip", new Unequip());
        registerCommand("nsfw", new WhyAreYouLikeThis());
        registerCommand("stats", new Statsheet());
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
        String playerName;
        do {
            render("Name your character: ");
            playerName = keyboard.nextLine();
        } while(playerName.trim().length() == 0);
        initialize(playerName);
    }
    public void initialize(String playerName) {
        player = new Player(playerName.trim());
        world = WorldBuilder.build(this);

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

        executeTurn(input);
        
    }
    public void executeTurn(String input) {
        executeCommand(input);

        world.update(this);
        player.update(this);

        if(player.isDead()) {
            render("You died.");
            end();
        } else {
            render("\nThe adventure of " + player.getName() + " continues. HP: " + player.getStat(Stats.HP));
        }
    }
    
    public World getMap() {
        return world;
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
        output.render(text);
    }
    public void render() {
        output.render("");
    }

    public Location getCurrentLocation() {
        return world.getCurrentLocation(player);
    }
    public boolean isInitialized() {
        return player != null;
    }
    public boolean isGameOver() {
        return player.getStat(Stats.HP) <= 0;
    }
}
