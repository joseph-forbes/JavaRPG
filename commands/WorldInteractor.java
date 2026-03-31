package commands;

import gameengine.Engine;
import util.commandutil.EntityFinder;

public class WorldInteractor implements Command {
    protected String man;
    protected EntityFinder finder;
    public String getMan() {
        return man;
    }
    public void execute(Engine game, String[] args) {


    }
}
