package Entities;

public class Entity {
    protected String description = "You see a generic thing.";
    protected String detailedDescription = "It's literally just a thing. There is no description beyond that, I'm not sure what you're asking for.";
    
    protected String name = "thing";

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getDetailedDescription() {
        return detailedDescription;
    }
}
