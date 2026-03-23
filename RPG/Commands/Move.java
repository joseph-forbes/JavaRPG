package Commands;
import Game.Adventure;
import Player.Player;

enum Direction {
    NORTH, EAST, SOUTH, WEST
}

public class Move implements Command {
    public String man = "Move the player. Takes in a direction (north, east, south, west).";
    Player player;

    public void execute(Adventure game, String[] args) {
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
        } catch (IllegalArgumentException e) {
            System.out.println("Please input a valid direction (north, south, east, or west).");
        }
        System.out.println("You move " + args[0].toLowerCase() + ".");
    }
}
