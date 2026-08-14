package commands;

import entities.Creature;
import entities.Entity;
import gameengine.Engine;
import player.Player;
import util.returnsutil.EntityFindReturn;

public class Hit extends WorldInteractor {
    
    private Player player;
    public Hit() {
        man = "Attack something with your equipped weapon. \n" 
                      + "Takes in an enemy (e.g. goblin, troll). \n"
                      + "If there is more than one, also takes an index (1,2,3, etc.) to clarify which enemy is being attacked.";
        commandName = "hit";
    }

    
    @Override
    protected void interact(Engine game, EntityFindReturn output) {
        player = game.getPlayer();
        Entity entity = output.entity;
        if(entity instanceof Creature) {
            // Combat

            Creature enemy = (Creature) entity;
            player.hit(enemy);
        } else {
            // Not a creature
            game.render("The " + output.searchStr + " seems unimpressed by your pathetic flailing.");
        }
    }
}
