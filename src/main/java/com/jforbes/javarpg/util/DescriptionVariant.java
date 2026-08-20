package com.jforbes.javarpg.util;

import java.util.function.Predicate;

import com.jforbes.javarpg.gameengine.Engine;

public class DescriptionVariant {

    public final String text;
    private final Predicate<Engine> condition;

    public DescriptionVariant(String text, Predicate<Engine> condition) {
        this.text = text;
        this.condition = condition;
    }

    public boolean applies(Engine game) {
        return condition.test(game);
    }
}