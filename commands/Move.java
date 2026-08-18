package commands;

import gameengine.Engine;
import player.Player;
import util.LocationId;
import util.enums.Direction;
import worldmap.Location;


public class Move implements Command {
    public String getMan() {
        return "Move the player. Takes in a direction (north, east, south, west).";
    }
    Player player;

    public void execute(Engine game, String[] args) {
        if(args.length > 0) {
            Direction direction;
            switch (args[0].toLowerCase()) {
                case "n":
                case "up":
                case "north":
                    direction = Direction.NORTH;
                break;
                case "s":
                case "down":
                case "south":
                    direction = Direction.SOUTH;
                break;
                case "e":
                case "right":
                case "east":
                    direction = Direction.EAST;
                break;
                case "w":
                case "left":
                case "west":
                    direction = Direction.WEST;
                break;
                default:
                    game.render("Please give a valid direction (north, south, east, or west)");
                    return;
            }
            player = game.getPlayer();

            Location currentLocation = game.getMap().getCurrentLocation(player);
            if(currentLocation.getExit(direction) != null) {
                // Found location
                LocationId newLocation = currentLocation.get(direction);
                player.setLocation(newLocation);
                game.render("You move " + direction.toString().toLowerCase());
                game.executeCommand("look around");
            } else { // Can't move that direction
                game.render("You attempt to move " + direction.toString().toLowerCase() + " but cannot.");
                game.render(currentLocation.getValidDirs());
            }
        } else {
            game.render("Please provide a direction (north, south, east, or west).");
        }
    }
}
