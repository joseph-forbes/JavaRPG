package com.jforbes.javarpg.util;

import java.util.List;

import com.jforbes.javarpg.entities.Entity;

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
}
