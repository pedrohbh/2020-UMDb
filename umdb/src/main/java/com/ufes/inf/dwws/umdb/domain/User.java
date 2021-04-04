package com.ufes.inf.dwws.umdb.domain;
import javax.persistence.*;
import com.ufes.inf.dwws.umdb.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    private Role role;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private Date registrationDate;

    public User(String name, String email, String password, Role role) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
        this.role = role;
    }
    public User() { }

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.getRole());

        return roles;
    }

    public String getPassword() { return password; }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) { this.password = password; }

    public List<String> getRoles() {
        List<String> r = new LinkedList<>();
        r.add(role.getName());
        return r;
    }

    public Role getRole(){
        return role;
    }
    public void setRole(Role role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDate() { return registrationDate; }
    public void setDate(Date registrationDate) { this.registrationDate = registrationDate; }
}
