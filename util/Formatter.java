package util;

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
}
