package com.jforbes.javarpg.util;

import java.util.List;

import com.jforbes.javarpg.entities.Entity;
import com.jforbes.javarpg.items.Item;
import com.jforbes.javarpg.player.Inventory;

public class Formatter {
    public static String needsAn(String followingWord) {
        return ("aeiou".indexOf(followingWord.toLowerCase().charAt(0)) != -1) ? "n " : " ";
    }
    public static String needsPlural(double num) {
        return num == 1 ? "" : "s";
    }
    public static String format(String word) {
        return word.toLowerCase();
    }
    public static String[] format(List<Entity> mapContents) {
        String[] strings = new String[mapContents.size()];
        for(int i=0; i<mapContents.size(); i++) {
            strings[i] = (mapContents.get(i).getName());
        }
        return strings;
    }
    public static String[] format(Inventory inventory) {
        List<Item> contents = inventory.contents;
        String[] items = new String[contents.size()];

        for(int i = 0; i < contents.size(); i ++ ) {
            items[i] = contents.get(i).getName();
        }

        return items;
    }
    public static String formatDirections(String[] validDirs) {
        if(validDirs.length > 0) {
            String directionText = "You can currently move: ";
            for(int i = 0; i < validDirs.length; i++) {
                directionText += validDirs[i];
                directionText += ", ";
            }
            if(directionText.length() > 0) directionText = directionText.substring(0, directionText.length() - 2); // remove final comma
            directionText += ".";
            return directionText;
        } else {
            return "You look all around but you can't seem to move in any direction";
        }
    }
}
