package worldmap;

import entities.*;
import gameengine.Engine;
import items.ammo.*;
import player.Player;
import util.WorldFinder;
import util.enums.Direction;

public class WorldBuilder {
    public static World build(Engine game) {
        World world = new World();

        //Location home = buildHome();
        //game.getPlayer().setLocation(home.getLocationId());
        //world.add(home.getLocationId(), home);

        Location village = buildVillage(game, world);
        world.add(village.getLocationId(), village);




        return world;
    }

    private static Location buildHome() {
        Location home = new Location("home");
        home.setDefaultDescription("This is your home. It is very homely.");
        

        home.addEntity(new Corndog());
        home.addEntity(new Entity("Pickle statue"));
        home.addDescription(
            "This place no longer feels like home because some hooligan took all your corndogs.", 
            engine -> WorldFinder.find(home, Corndog.class) == null
        );

        return home;
    }
    private static Location buildVillage(Engine game, World world) {
        Player player = game.getPlayer();

        Location village = new Location("village");
        village.setDefaultDescription("A bustling village full of all your friends and family.");

        Location home = buildHome();
        House homeEntity = new House(
            "home", 
            "You've lived here as long as you can remember", 
            "You see your home", 
            home.getLocationId()
        );
        village.addEntity(homeEntity);
        player.setLocation(home.getLocationId());
        world.add(home.getLocationId(), home);

        //village.connect(Direction.NORTH, home.getLocationId());
        home.connect(Direction.SOUTH, village.getLocationId());


        return village;
    }
}
