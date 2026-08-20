package com.jforbes.javarpg.util.returnsutil;

import com.jforbes.javarpg.entities.Entity;

public class EntityFindReturn {
    public final String error;
    public final Entity entity;
    public final String searchStr;

    public EntityFindReturn(Entity entity, String error, String searchStr) {
        this.entity = entity;
        this.error = error;
        this.searchStr = searchStr;
    }

    public static EntityFindReturn success(Entity entity, String searchStr) {
        return new EntityFindReturn(entity, null, searchStr);
    }
    public static EntityFindReturn failure(String error, String searchStr) {
        return new EntityFindReturn(null, error, searchStr);
    }


}
