package util;

public class LocationId {
    private String id;
    public LocationId(String value) {
        id = value;
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Location ID cannot be null or blank");
        }
    }
}
