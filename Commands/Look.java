package Commands;
import WorldMap.*;
import Entities.Entity;

import Game.Engine;

public class Look implements Command {
    public String man = "Look at an entity. Takes the name of the entity. Type \"look around\" to see everything around you.";
    public String getMan() {
        return man;
    }
    
    WorldMap worldMap;
    String playerName;

    public void execute(Engine game, String[] args) {
        worldMap = game.getMap();
        playerName = game.getPlayer().getName();

        MapTile currentMapTile = worldMap.getCurrentMapTile();
        if(args[0].toLowerCase().equalsIgnoreCase("around")) {
            // "look around"
            if(currentMapTile.contents.size() == 0) {
                System.out.println("The world around you is eerily silent. You see nothing.");
            }
            for( Entity entity : currentMapTile.contents) {
                System.out.println(entity.getDescription());
            }
        } else {
            boolean didDisplay = false;
            for( Entity entity : currentMapTile.contents) {
                if(entity.getName().equalsIgnoreCase(args[0])) {
                    System.out.println(entity.getDetailedDescription()); // is detailed
                    didDisplay = true;
                }
            }

            // Edge cases
            if(args[0].equalsIgnoreCase(playerName) || args[0].equalsIgnoreCase("self")) {
                System.out.println("   ----------       ");
                System.out.println("  --        --      ");
                System.out.println(" --  .   .   --     ");
                System.out.println(" --          --     ");
                System.out.println(" --    u     --     ");
                System.out.println("  --        --     This is you!");
                System.out.println("   ----------   You look at yourself.");
                System.out.println("       ||           ");
                System.out.println("       ||/          ");
                System.out.println("      /||           ");
                System.out.println("       ||           ");
                System.out.println("       ||           ");
                System.out.println("      /  \\           ");
            } else if(args.length == 0) {
                System.out.println("Please provide something to look at. For example, \"look goblin\"");
            } else if(!didDisplay) {
                char firstChar = args[0].charAt(0);
                System.out.println("Could not find a" + ("aeiou".indexOf(firstChar) != -1 ? "n" : "") + " " + args[0] + ". Check your spelling, or type \"look around\" to see if there is one nearby.");
            }
        }
    }
}