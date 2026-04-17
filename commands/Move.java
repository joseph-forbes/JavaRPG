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
                    player.changePos(Positions.X, 1);
                break;
                case SOUTH:
                    player.changePos(Positions.Y, -1);
                break;
                case WEST:
                    player.changePos(Positions.X, -1);
                break;
            }
            game.render("You move " + args[0].toLowerCase() + ". You look around.");
            game.executeCommand("look around");
        } catch (IllegalArgumentException e) {
            game.render("Please input a valid direction (north, south, east, or west).");
        }
    }
}
