package worldmap;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import entities.Entity;
import gameengine.Engine;
import util.DescriptionVariant;
import util.LocationId;
import util.enums.Direction;

public class Location {
    private LocationId id;
    private String defaultDescription;
    private List<DescriptionVariant> descriptions;
    private Map<Direction, LocationId> exits; 
    private List<Entity> contents;

    public Location(String id) {
        this(id, "");
    }
    public Location(String id, String defaultDescription) {
        this.id = new LocationId(id);
        this.defaultDescription = defaultDescription;
        descriptions = new ArrayList<DescriptionVariant>();
        exits = new EnumMap<Direction, LocationId>(Direction.class);
        contents = new ArrayList<Entity>();
    }
    
    public Location() {
        contents = new ArrayList<Entity>();
    }

    public void update(Engine game) {
        for(Entity entity : contents) {
            entity.update(game);
        }
        collectGarbage();
    }
    
    private void collectGarbage() {
        for(int i=contents.size()- 1; i>= 0; i--) {
            Entity entity = contents.get(i);
            if(entity.isDead()) {
                contents.remove(i);
            }
        }
    }
    public LocationId getLocationId() {
        return id;
    }
    public String getDescription(Engine game) {
        for(DescriptionVariant variant : descriptions) {
            if(variant.applies(game)) {
                return variant.text;
            }
        }
        return defaultDescription;
    }
    public LocationId get(Direction dir) {
        return exits.get(dir);
    }
    public Map<Direction, LocationId> getExits() {
        return exits;
    }
    public LocationId getExit(Direction dir) {
        return exits.get(dir);
    }

    public List<Entity> getContents() {
        return contents;
    }
    public void addEntity(Entity entity) {
        contents.add(entity);
    }
    public void setDefaultDescription(String text) {
        defaultDescription = text; 
    }
    public void addDescription(String text, Predicate<Engine> condition) {
        descriptions.add(new DescriptionVariant(text, condition));
    }
    public void connect(Direction direction, LocationId location) {
        exits.put(direction, location);
    }

    public String getValidDirs() {
        if(exits.keySet().size() > 0) {
            String validDirs = "";
            for(Direction dir : exits.keySet()) {
                validDirs += dir.toString().toLowerCase();
                validDirs += ", ";
            }
            if(validDirs.length() > 0) validDirs = validDirs.substring(0, validDirs.length() - 2); // remove final comma
            return "You can currently move: " + validDirs + ".";
        } else {
            return "You look all around but you can't seem to move in any direction";
        }
    }
}
