package Commands;

import Game.Engine;
public class Die implements Command {
    String man = "End your own life";
    public void execute(Engine game, String[] args) {
        System.out.println("You repeatedly bash yourself over the head.");
        game.end();
    }
}
