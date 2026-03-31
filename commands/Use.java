package commands;

import gameengine.Engine;

public class Use implements Command {
    public String getMan() {
        return "Use an item. Takes in an item in your inventory.";
    }
    public void execute(Engine game, String[] args) {

    }
}
