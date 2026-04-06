package commands;

import entities.Creature;
import entities.Entity;
import gameengine.Engine;
import player.Player;
import util.Formatter;
import util.returnsutil.CombatReturn;
import util.returnsutil.EntityFindReturn;

public class Hit extends WorldInteractor {
    public String getMan() {
        return "Attack something with your equipped weapon. \n" 
                      + "Takes in an enemy (e.g. goblin, troll). \n"
                      + "If there is more than one, also takes an index (1,2,3, etc.) to clarify which enemy is being attacked.";
    }
    
    private Player player;

    
    @Override
    protected void interact(Engine game, EntityFindReturn output) {
        player = game.getPlayer();
        Entity entity = output.entity;
        if(entity instanceof Creature) {
            Creature enemy = (Creature) entity;
            CombatReturn outcome = enemy.takeHit(player);
            if(outcome.getSubject().equals("opponent") && outcome.getDamage() > 0) {
                // Player was subject
                game.render("You missed so badly you hit yourself. You took " + outcome.getDamage() + " damage.");
            } else if(outcome.getSubject().equals("opponent")) {
                game.render("You missed!");
            } else {
                game.render(
                    "The " + enemy.getName() + 
                    " took " + outcome.getDamage() + 
                    " damage" + ((enemy.getStat("hp") > 0) ? ", and has " + enemy.getStat("hp") + " hp remaining." : ". The "  + enemy.getName() + " died!")
                );
            if(enemy.isDead() && enemy.getStat("xpOnDeath") > 0) {
                player.changeXp(enemy.getStat("xpOnDeath"));
                game.render("You earned " + enemy.getStat("xpOnDeath") + " xp.");
            }
            }
        } else {
            handleExceptions(game, EntityFindReturn.failure("notcreature", output.searchStr));
        }
    }

    @Override
    protected void handleExceptions(Engine game, EntityFindReturn output) {
        // Possible exceptions: noentityprovided, nosuchentity notcreature.
        String name = output.searchStr;
        switch (output.error) {
            case "noentityprovided":
                game.render("Please provide a creature, i.e. \"hit goblin\"");
            break;
            case "nosuchentity":
                game.render("Could not find a" + Formatter.needsAn(name) + name + 
                ". Check your spelling and try again, or type \"look around\" to see what you can hit"
            );
            break;
            case "notcreature":
                game.render("The " + name + " seems unimpressed by your pathetic flailing.");
            break;
            default:
                game.render("An unknown error occured. Please try again.");
            break;
        }
    }

}
