package util.returnsutil;

import entities.Entity;

public class EntityFindReturn {
    public final String error;
    public final Entity entity;

    public EntityFindReturn(Entity entity, String error) {
        this.entity = entity;
        this.error = error;
    }


}
