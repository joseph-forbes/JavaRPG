package Commands;

import java.util.ArrayList;

import util.Returns.ManReturn;

public class Help implements Command {
    public String getMan() {
        return "Prints this list";
    }
    public void execute(Game.Engine game, String[] args) {
        ArrayList<ManReturn> mans = (ArrayList<ManReturn>) game.getManList();
        for(ManReturn man : mans) {
            String nameStr = man.name.toLowerCase();
            String firstChar = (nameStr.charAt(0) + "").toUpperCase();
            nameStr = firstChar + nameStr.substring(1, nameStr.length()); 

            System.out.println(
                nameStr + 
                ": " + man.man
            );
            System.out.println("------------------------");
        }

    }
}