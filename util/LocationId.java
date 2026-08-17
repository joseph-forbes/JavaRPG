package util;

public record LocationId(String value) {

    public LocationId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Location ID cannot be null or blank");
        }
    }
}
