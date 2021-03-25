package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String email, String password){
        List<User> d = this.userRepository.findByEmail(email);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.userRepository.save(new User(name, email, password));
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

    public User updateUserById(Long id, String name, String email, String password) {
        Optional<User> d = this.userRepository.findById(id);

        if (d.isPresent()) {
            if (name != "") { d.get().setName(name); }
            if (email != "") { d.get().setEmail(email); }
            if (password != "") { d.get().setPassword(password); }
            this.userRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
