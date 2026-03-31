package util.commandutil;

import java.util.ArrayList;

import entities.Entity;
import worldmap.MapTile;
import worldmap.WorldMap;

public class EntityFinder {
    public Entity findEntity(String[] args, WorldMap worldMap) {
        Entity entity;
        MapTile currentTile;

        String searchStr = "";
        int entityIdx = -1;
        if(args.length > 0) {
            for(String word : args) {
                try {
                    entityIdx = Integer.parseInt(word);
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

        for(Entity e : currentTile.contents) {
            if(e.getName().equalsIgnoreCase(searchStr)) {
                // This is the thing you're after
                potentialEnemies.add(e);
            }
        }
        if(potentialEnemies.size() == 0) {
            return null;
        } else if(potentialEnemies.size() == 1) {
            // Only one possible target
            entity = potentialEnemies.get(0);
        } else {
            if(args.length > 1) {
                try {
                    entity = potentialEnemies.get(entityIdx);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException();
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException();
                }
            } else {
                entity = potentialEnemies.get(0);
            }
        }

        return entity;
    } 
}