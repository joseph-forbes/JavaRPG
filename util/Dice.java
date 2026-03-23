package util;

import java.util.Random;

public class Dice {
    Random generator = new Random();

    public int roll(int faces) {
        return generator.nextInt(faces) + 1;
    }
}
