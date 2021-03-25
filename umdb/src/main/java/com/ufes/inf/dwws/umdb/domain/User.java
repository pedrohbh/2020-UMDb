package com.ufes.inf.dwws.umdb.domain;
import javax.persistence.*;
import com.ufes.inf.dwws.umdb.domain.Role;
import java.util.Date;

@Entity
public class User {
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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
    }
    public User() { }

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDate() { return registrationDate; }
    public void setDate(Date registrationDate) { this.registrationDate = registrationDate; }

}
