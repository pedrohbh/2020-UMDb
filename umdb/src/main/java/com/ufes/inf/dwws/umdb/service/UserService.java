package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.persistence.UserRepository;
import com.ufes.inf.dwws.umdb.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import com.ufes.inf.dwws.umdb.security.WebSecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;
import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
//    TODO
//    @Autowired
//    WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
//    PasswordEncoder passwordEncoder = webSecurityConfig.encoder();

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String email, String password){
        List<User> d = this.userRepository.findByEmail(email);
        Role role = roleRepository.findByName("user").get(0);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.userRepository.save(new User(name, email, password, role));
        }
    }

    public List<User> findAll(){
        return  this.userRepository.findAll();
    }

    public User findUserById (Long id) {
        Optional<User> d = this.userRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public User deleteUserById(Long id) {
        Optional<User> d = this.userRepository.findById(id);

        if (d.isPresent()) {
            this.userRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public User updateUserById(Long id, String name, String email, String password, String role) {
        Optional<User> d = this.userRepository.findById(id);

        if (d.isPresent()) {
            if (name != "") { d.get().setName(name); }
            if (email != "") { d.get().setEmail(email); }
            if (password != "") { d.get().setPassword(password); }
            if (role != "") { d.get().setRole(roleRepository.findByName(role).get(0)); }
            this.userRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
