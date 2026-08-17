package entities;

public class Entity {

    protected boolean isEnemy = false;
    protected String description;
    protected String detailedDescription;
    protected String name = "thing";
    private String oDescription;
    private String oDetailedDescription;

    public Entity() {
        this("Generic Thing");
    }
    public Entity(String name) {
        this(name, "There does not seem to be anything particularly special about the " + name + " and you get the vibe it exists purely as filler content.");
    }
    public Entity(String name, String detailedDescription) {
        this(name, detailedDescription, "You see a " + name.toLowerCase() + ".");
    }
    public Entity(String name, String detailedDescription, String description) {
        this.name = name;
        oDescription = this.description = description;
        oDetailedDescription = this.detailedDescription = detailedDescription;
        setDescription();
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    protected void setDescription() {
        description = oDescription;
        detailedDescription = oDetailedDescription;
    }
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void update() {
        updateLogic();
        updateRender();
    }

    protected void updateLogic() {

    }
    protected void updateRender() {
        setDescription();
    }

    public boolean isDead() {
        return false;
    }

    public String toString() {
        setDescription();
        return detailedDescription;
    }
}
