package Commands;

import java.util.ArrayList;

import util.ManReturn;

public class Help implements Command {
    public String man = "Prints this list";
    public String getMan() {
        return man;
    }
    public void execute(Game.Engine game, String[] args) {
        ArrayList<ManReturn> mans = (ArrayList<ManReturn>) game.getManList();
        for(ManReturn man : mans) {
            String nameStr = man.getCommandName().toLowerCase();
            String firstChar = (nameStr.charAt(0) + "").toUpperCase();
            nameStr = firstChar + nameStr.substring(1, nameStr.length()); 

            System.out.println(
                nameStr + 
                ": " + man.getMan()
            );
            System.out.println("------------------------");
        }

    }
}