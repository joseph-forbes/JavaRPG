package entities;

public class Entity {

    protected boolean isEnemy = false;
    protected String description;
    protected String detailedDescription;

    public Entity() {
        setDescription();
    }
    
    protected String name = "thing";

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    protected void setDescription() {
        description = "You see a generic thing.";
        detailedDescription = "It's literally just a thing. There is no description beyond that, I'm not sure what you're asking for.";
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


}
