package worldmap;

import entities.*;
import gameengine.Engine;
import items.ammo.*;
import player.Player;
import util.WorldFinder;

public class WorldBuilder {
    public static World build(Engine game) {
        Player player = game.getPlayer();
        World world = new World();

        Location home = new Location("home");

        home.setDefaultDescription("This is your home. It is very homely.");
        

        home.addEntity(new Corndog());
        home.addEntity(new Entity("Pickle statue"));
        home.addDescription(
            "This place no longer feels like home because some hooligan took all your corndogs.", 
            engine -> WorldFinder.find(home, Corndog.class) == null
        );

        player.setLocation(home.getLocationId());

        world.add(home.getLocationId(), home);

        return world;
    }
}
