package worldmap;

import entities.*;
import items.ammo.*;
import player.Player;

public class WorldBuilder {
    public static World build(Player player) {
        World world = new World();

        Location home = new Location("home");

        home.setEntryText("This is your home. It is very homely.");

        home.addEntity(new Corndog());
        home.addEntity(new Entity("Pickle statue"));
        player.setLocation(home);

        return world;
    }
}
