package com.epam.testing.model.entity;

/**
 * Abstract entity class
 *
 * @author rom4ik
 */

public class Entity {
    private long id;

    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
