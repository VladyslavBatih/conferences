package db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Comparable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}