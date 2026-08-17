package worldmap;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import util.LocationId;
import util.enums.Direction;

public class Location {
    private LocationId id;
    private String entryText;
    private Map<Direction, LocationId> exits; 
    private List<Entity> contents;

    public Location(String id) {
        this(id, "");
    }
    public Location(String id, String entryText) {
        this(id, entryText, new EnumMap<Direction,LocationId>(Direction.class));
    }
    public Location(String id, String entryText, Map<Direction, LocationId> exits) {
        this(id, entryText, exits, new ArrayList<>());
    }
    public Location(String id, String entryText, Map<Direction, LocationId> exits, List<Entity> contents) {
        this.id = new LocationId(id);
        this.entryText = entryText;
        this.exits = exits;
        this.contents = contents;
    }
    
    public Location() {
        contents = new ArrayList<Entity>();
    }

    public void update() {
        for(Entity entity : contents) {
            entity.update();
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
    public String getEntryText() {
        return entryText;
    }
    public LocationId get(Direction dir) {
        return exits.get(dir);
    }
    public Map<Direction, LocationId> getExits() {
        return exits;
    }

    public List<Entity> getContents() {
        return contents;
    }
    public void addEntity(Entity entity) {
        contents.add(entity);
    }
    public void setEntryText(String text) {
        entryText = text; 
    }
    public void connect(Direction direction, LocationId location) {
        exits.put(direction, location);
    }
}
