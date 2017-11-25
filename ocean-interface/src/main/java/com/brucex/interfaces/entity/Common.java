package com.brucex.interfaces.entity;

import java.io.Serializable;

public abstract class Common implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
