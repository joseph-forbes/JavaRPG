package Commands;

import Game.Adventure;
public class Die implements Command {
    String man = "End your own life";
    public void execute(Adventure game, String[] args) {
        System.out.println("You repeatedly bash yourself over the head.");
        game.end();
    }
}
