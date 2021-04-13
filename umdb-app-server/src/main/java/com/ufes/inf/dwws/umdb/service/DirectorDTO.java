package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Director;

public class DirectorDTO {

    private long id;
    private String name;

    public DirectorDTO( Director director){
        this.id = director.getId();
        this.name = director.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
