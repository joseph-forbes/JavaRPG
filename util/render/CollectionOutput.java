package util.render;

import java.util.List;

public record CollectionOutput(
    List<String> messages, 
    int playerHP, 
    boolean isPlayerDead
) implements GameOutput {
    @Override
    public void render(String text) {
        messages.add(text);
    }
}
