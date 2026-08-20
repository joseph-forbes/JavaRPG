package com.jforbes.javarpg.util.commandutil;

import java.util.ArrayList;

import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.items.Item;
import com.jforbes.javarpg.player.Inventory;
import com.jforbes.javarpg.util.returnsutil.EntityFindReturn;
import com.jforbes.javarpg.worldmap.Location;

public class EntityFinder {
    public EntityFindReturn find(String[] args, Location tile) {
        Entity entity;

        String searchStr = "";
        int entityIdx = -1;
        if(args.length > 0) { // User provided multiple words
            for(String word : args) {
                try {
                    entityIdx = Integer.parseInt(word) - 1;
                    // is an integer
                    break;
                } catch (NumberFormatException e) {
                    // not an integer; continue.
                }
                searchStr += word + " ";
            }
            searchStr = searchStr.trim(); // remove trailing whitespace
        }
        if(searchStr.length() == 0) {
            return EntityFindReturn.failure("noentityprovided", searchStr);
        }
        
        ArrayList<Entity> potentialEntities = new ArrayList<>();

        for(Entity e : tile.getContents()) {
            if(e.getName().equalsIgnoreCase(searchStr)) {
                // This is the thing you're after
                potentialEntities.add(e);
            }
        }
        if(potentialEntities.size() == 0) {
            return EntityFindReturn.failure("nosuchentity", searchStr);
        } else if(potentialEntities.size() == 1) {
            // Only one possible target
            entity = potentialEntities.get(0);
        } else {
            if(entityIdx != -1) {
                // Multiple potential options - listen to which the user wants
                try {
                    entity = potentialEntities.get(entityIdx);
                } catch (IndexOutOfBoundsException e) {
                    entity = potentialEntities.get(0); // Default to the first one bc I'm too lazy to come up with an error message
                }
            } else {
                // User doesn't care which, just give them the first one
                entity = potentialEntities.get(0);
            }
        }

        return EntityFindReturn.success(entity, searchStr);
    } 
    public EntityFindReturn find(String[] args, Inventory inventory) {
        Item item;

        String searchStr = "";
        int itemIdx = -1;
        if(args.length > 0) { // User provided multiple words
            for(String word : args) {
                try {
                    itemIdx = Integer.parseInt(word);
                    // is an integer
                    break;
                } catch (NumberFormatException e) {
                    // not an integer; continue.
                }
                searchStr += word + " ";
            }
            searchStr = searchStr.substring(0, searchStr.length() - 1); // remove trailing whitespace
        }
        if(searchStr.length() == 0) {
            return EntityFindReturn.failure("noitemprovided", searchStr);
        }
        
        ArrayList<Item> potentialItems = new ArrayList<>();

        for(Item i : inventory.contents) {
            if(i.getName().equalsIgnoreCase(searchStr)) {
                // This is the thing you're after
                potentialItems.add(i);
            }
        }
        if(potentialItems.size() == 0) {
            return EntityFindReturn.failure("nosuchitem", searchStr);
        } else if(potentialItems.size() == 1) {
            // Only one possible target
            item = potentialItems.get(0);
        } else {
            if(itemIdx != -1) {
                // Multiple potential options - listen to which the user wants
                try {
                    item = potentialItems.get(itemIdx);
                } catch (IndexOutOfBoundsException e) {
                    item = potentialItems.get(0); // Default to the first one bc I'm too lazy to come up with an error message
                }
            } else {
                // User doesn't care which, just give them the first one
                item = potentialItems.get(0);
            }
        }

        return EntityFindReturn.success(item, searchStr);
    } 


}