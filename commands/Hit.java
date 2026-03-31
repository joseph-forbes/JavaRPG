package commands;

import java.util.ArrayList;

import entities.Creature;
import entities.Entity;
import gameengine.Engine;
import player.Player;
import util.returnsutil.CombatReturn;
import worldmap.*;

public class Hit implements Command {
    public String getMan() {
        return "Attack something with your equipped weapon. \n" 
                      + "Takes in an enemy (e.g. goblin, troll). \n"
                      + "If there is more than one, also takes an index (1,2,3, etc.) to clarify which enemy is being attacked.";
    }
    
    private WorldMap worldMap;
    private MapTile currentTile;
    private Player player;

    public void execute(Engine game, String[] args) {

        worldMap = game.getMap();
        player = game.getPlayer();

        String searchStr = "";
        if(args.length > 0) {
            for(String word : args) {
                try {
                    Integer.parseInt(word);
                    // is an integer
                    break;
                } catch (NumberFormatException e) {
                    // not an integer; continue.
                }
                searchStr += word + " ";
            }
            searchStr = searchStr.substring(0, searchStr.length() - 1); // remove trailing whitespace
        } else {
            System.out.println("Please provide something to hit. For example, \"hit goblin\"");
        }
        
        currentTile = worldMap.getCurrentMapTile();
        ArrayList<Entity> potentialEnemies = new ArrayList<>();

        for(Entity entity : currentTile.contents) {
            if(entity.getName().equalsIgnoreCase(searchStr)) {
                // This is the thing you're after
                potentialEnemies.add(entity);
            }
        }
        if(potentialEnemies.size() == 0) {
            System.out.println("You try to hit the " + searchStr.toLowerCase() + " but can't see one nearby.");
        } else if(potentialEnemies.size() == 1) {
            // Only one possible target
            hit(potentialEnemies.get(0), game);
        } else {
            if(args.length > 1) {
                try {
                    int index = Integer.parseInt(args[1]) - 1;
                    hit(potentialEnemies.get(index), game);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid index (1,2,etc.)");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("There are only " + potentialEnemies.size() + " enemies with that name. Please input a valid index");
                }
            } else {
                hit(potentialEnemies.get(0), game);
            }
        }

    }
    private void hit(Entity entity, Engine game) {
        if(entity instanceof Creature) {
            Creature enemy = (Creature) entity;
            CombatReturn outcome = enemy.takeHit(player);
            if(outcome.getSubject().equals("opponent") && outcome.getDamage() > 0) {
                // Player was subject
                System.out.println("You took " + outcome.getDamage() + " damage.");
            } else if(outcome.getSubject().equals("opponent")) {
                System.out.println("You missed.");
            } else {
                System.out.println("The " + enemy.getName() + " took " + outcome.getDamage()
                + " damage" + ((enemy.getStat("hp") > 0) ? ", and has " + enemy.getStat("hp") + " hp remaining." : ". The "  + enemy.getName() + " died!")
            );
            if(enemy.isDead() && enemy.getStat("xpOnDeath") > 0) {
                System.out.println("You earned " + enemy.getStat("xpOnDeath") + " xp.");
                player.changeXp(enemy.getStat("xpOnDeath"));
            }
            }
        } else {
            System.out.println("The " + entity.getName().toLowerCase() + " seems unimpressed by your pathetic flailing.");
        }
    }
}
