package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.domain.User;

import java.util.List;

public class UserDTO {

    private long id;
    private String name;
    private String email;
    private String token;
    private List<String> roles;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
