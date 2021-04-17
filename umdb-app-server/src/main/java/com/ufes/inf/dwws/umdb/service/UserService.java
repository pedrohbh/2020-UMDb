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
//import com.ufes.inf.dwws.umdb.security.WebSecurityConfig;
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

    public UserDTO saveUser(String name, String email, String password){
        User user = this.userRepository.findByEmail(email);
        Role role = roleRepository.findByName("ROLE_USER");

        if (user != null) {
            return null;
        } else {
            this.userRepository.save(new User(name, email, passwordEncoder.encode(password), role));
            return sigin(email, password);
        }
    }

    public List<UserDTO> findAll(){
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        users.forEach(user -> {
            usersDTO.add(new UserDTO(user));
        });
        return usersDTO;
    }

    public UserDTO findUserById (Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            return new UserDTO(user.get());
        } else {
            return null;
        }
    }

    public Boolean deleteUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public UserDTO updateUserById(Long id, String name, String email, String password) {
        Optional<User> user = this.userRepository.findById(id);

        if (this.userRepository.findByName(name) != null || this.userRepository.findByEmail(email) != null){
            return null;
        }

        if (user.isPresent()) {
            if (name != "" ) { user.get().setName(name); }
            if (email != "") { user.get().setEmail(email); }
            if (password != "") { user.get().setPassword(passwordEncoder.encode(password)); }
            this.userRepository.save(user.get());
            return new UserDTO(user.get());
        } else {
            return null;
        }
    }

    public UserDTO updateUserById(Long id, String role) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            if (role != "") { user.get().setRole(roleRepository.findByName(role)); }
            this.userRepository.save(user.get());
            return new UserDTO(user.get());
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

    public UserDTO sigin (String userName, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        User user  = this.userRepository.findByEmail(userName);
        if (user == null){
            return null;
        }
        String token = jwtTokenProvider.createToken(userName, user.getRoles());
        UserDTO userDTO = new UserDTO(user);
        userDTO.setToken(token);
        return userDTO;
    }
}
