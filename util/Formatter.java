package util;

public class Formatter {
    public static String needsAn(String followingWord) {
        return ("aeiou".indexOf(followingWord.toLowerCase().charAt(0)) != -1) ? "n " : " ";
    }
}
