package entities;

import util.LocationId;

public class House extends Entity {
    private final LocationId id;
    public House(String name, String detailedDescription, String description) {
        this(name, detailedDescription, description, new LocationId(name));
    }
    public House(String name, String detailedDescription, String description, LocationId id) {
        super(name, detailedDescription, description);
        this.id = id;
    }

    public LocationId enter() {
        return id;
    }

}
