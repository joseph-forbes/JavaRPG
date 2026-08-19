package commands;

import entities.Creature;
import entities.Entity;
import gameengine.Engine;
import player.Player;

public class Hit extends WorldInteractor {
    
    private Player player;
    public Hit() {
        man = "Attack something with your equipped weapon. \n" 
                      + "Takes in an enemy (e.g. goblin, troll). \n"
                      + "If there is more than one, also takes an index (1,2,3, etc.) to clarify which enemy is being attacked.";
        commandName = "hit";
    }

    
    @Override
    protected void interact(Engine game, Entity entity) {
        player = game.getPlayer();
        if(entity instanceof Creature) {
            // Combat

            Creature enemy = (Creature) entity;
            player.hit(enemy, game);
        } else {
            // Not a creature
            game.render("The " + entity.getName().toLowerCase() + " seems unimpressed by your pathetic flailing.");
        }
    }
}
