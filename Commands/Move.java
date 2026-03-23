package Commands;
import Game.Engine;
import Player.Player;

enum Direction {
    NORTH, EAST, SOUTH, WEST
}

public class Move implements Command {
    public String man = "Move the player. Takes in a direction (north, east, south, west).";
    public String getMan() {
        return man;
    }
    Player player;

    public void execute(Engine game, String[] args) {
        player = game.getPlayer();

         try {
            Direction dir = Direction.valueOf(args[0].toUpperCase());
            switch (dir) {
                case NORTH:
                    player.changePos('y', 1);
                break;
                case EAST:
                    player.changePos('x', 1);
                break;
                case SOUTH:
                    player.changePos('y', -1);
                break;
                case WEST:
                    player.changePos('x', -1);
                break;
            }
            System.out.println("You move " + args[0].toLowerCase() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Please input a valid direction (north, south, east, or west).");
        }
    }
}
