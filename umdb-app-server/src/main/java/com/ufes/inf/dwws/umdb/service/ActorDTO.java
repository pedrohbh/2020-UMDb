package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Actor;

public class ActorDTO {

    private long id;
    private String name;

    public ActorDTO(Actor actor){
        this.id = actor.getId();
        this.name = actor.getName();
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
