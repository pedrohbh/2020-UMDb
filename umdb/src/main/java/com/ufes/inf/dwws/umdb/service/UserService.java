package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.persistence.UserRepository;
import com.ufes.inf.dwws.umdb.persistence.RoleRepository;
import com.ufes.inf.dwws.umdb.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.ufes.inf.dwws.umdb.security.WebSecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String email, String password){
        User d = this.userRepository.findByEmail(email);
        Role role = roleRepository.findByName("ROLE_USER");

        if (d == null) {
            return null;
        } else {
            return this.userRepository.save(new User(name, email, passwordEncoder.encode(password), role));
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
            if (password != "") { d.get().setPassword(passwordEncoder.encode(password)); }
            if (role != "") { d.get().setRole(roleRepository.findByName(role)); }
            this.userRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

    public User updateUserById(Long id, String role) {
        Optional<User> d = this.userRepository.findById(id);
        if (d.isPresent()) {
            if (role != "") { d.get().setRole(roleRepository.findByName(role)); }
            this.userRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }



    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(useremail);

        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    public Map sigin (String userName, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        User user  = this.userRepository.findByEmail(userName);
        if (user == null){
            return null;
        }
        String token = jwtTokenProvider.createToken(userName, user.getRoles());
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userName);
        model.put("token", token);
        return model;
    }



}
