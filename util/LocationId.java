package util;

public class LocationId {
    public final String id;
    public LocationId(String value) {
        id = value;
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Location ID cannot be null or blank");
        }
    }
    public boolean equals(LocationId otherLocationId) {
        return this.id.equals(otherLocationId.id); 
    }
    public boolean equals(String id) { 
        return this.id.equals(id);
    }
}
