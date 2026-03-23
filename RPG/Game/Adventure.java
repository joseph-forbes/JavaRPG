package Game;
import java.util.Scanner;

import WorldMap.WorldMap;
import Player.Player;

public class Adventure {
    boolean isRunning = true;
    Scanner keyboard = new Scanner(System.in);
    Player player;
    WorldMap worldMap;
    Engine gameEngine;
    public static void main(String[] args) {
        Adventure adventure = new Adventure();
        adventure.start();
        while(adventure.isRunning) {
            adventure.update();
        }

        adventure.keyboard.close();
    }
    void start() {
        System.out.print("Name your character: ");
        player = new Player(keyboard.nextLine());
        worldMap = new WorldMap(this);
        gameEngine = new Engine(this);

        System.out.println("This is the beginning of the adventure of " + player.getName() + '.');

        System.out.println();
        System.out.println("On your initial look around: ");
        gameEngine.executeCommand("look around"); // initially look at everything around you so you have a vibe of the world
        System.out.println();

        System.out.println("Type \"help\" for a list of commands.");
    }
    void update() {
        System.out.print("What do you want to do? ");
        String instruction = keyboard.nextLine();
        System.out.println();
        gameEngine.executeCommand(instruction);
        System.out.println();

        if(isRunning) System.out.println("The adventure of " + player.getName() + " continues.");
    }
    public void end() {
        isRunning = false;
        System.out.println("Thanks for playing!");
    }

    public WorldMap getMap() {
        return worldMap;
    }
    public Player getPlayer() {
        return player;
    }
}
