package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Genre;

public class GenreDTO {

    private long id;
    private String name;

    public GenreDTO(Genre gerne){
        this.id = gerne.getId();
        this.name = gerne.getName();
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
