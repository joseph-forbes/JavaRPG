package commands;
import gameengine.Engine;
import player.Player;
import util.enums.Directions;
import util.enums.Positions;


public class Move implements Command {
    public String getMan() {
        return "Move the player. Takes in a direction (north, east, south, west).";
    }
    Player player;

    public void execute(Engine game, String[] args) {
        player = game.getPlayer();

         try {
            Directions dir = Directions.valueOf(args[0].toUpperCase());
            switch (dir) {
                case NORTH:
                    player.changePos(Positions.Y, 1);
                break;
                case EAST:
                    player.changePos(Positions.Y, 1);
                break;
                case SOUTH:
                    player.changePos(Positions.Y, -1);
                break;
                case WEST:
                    player.changePos(Positions.Y, -1);
                break;
            }
            System.out.println("You move " + args[0].toLowerCase() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Please input a valid direction (north, south, east, or west).");
        }
    }
}
