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

            Location currentLocation = player.getLocation();
            if(currentLocation.getExits().containsKey(direction)) {
                // Found location
                LocationId newLocation = currentLocation.get(direction);
                player.setLocation(game.getMap().get(newLocation));
            } else { // Can't move that direction
                game.render("You attempt to move " + direction.toString().toLowerCase() + " but cannot.");
                String validDirs = "";
                for(Direction dir : currentLocation.getExits().keySet()) {
                    validDirs += dir.toString().toLowerCase();
                    validDirs += ", ";
                }
                if(validDirs.length() > 0) validDirs = validDirs.substring(0, validDirs.length() - 2); // remove final comma
                game.render("You can currently move: " + validDirs + ".");
            }
        } else {
            game.render("Please provide a direction (north, south, east, or west).");
        }
    }
}
