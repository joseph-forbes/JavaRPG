package com.jforbes.javarpg.util.enums;

import java.util.Random;

public enum Die {
    D2(2),D4(4),D6(6),D8(8),D10(10),D12(12),D20(20),D100(100);
    private Random generator;
    private int faces;

    private Die(int numSides) {
        faces = numSides;
        generator = new Random();
    }

    public int roll() {
        return generator.nextInt(faces) + 1;
    }
}
