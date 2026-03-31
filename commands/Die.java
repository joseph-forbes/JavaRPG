package commands;

import gameengine.Engine;
public class Die implements Command {
    public String getMan() {
        return "End your own life";
    }
    public void execute(Engine game, String[] args) {
        System.out.println("You repeatedly bash yourself over the head.");
        game.end();
    }
}
