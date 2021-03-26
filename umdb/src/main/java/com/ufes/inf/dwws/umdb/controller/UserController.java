package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/user")
    @ResponseBody
    public ResponseEntity<Object> saveUser (@RequestBody User user) {
        User d = this.userService.saveUser(user.getName(), user.getEmail(), user.getPassword());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um ator cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/user")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<Object> findUser(@PathVariable Long id) {
        User d = this.userService.findUserById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteUser (@PathVariable Long id) {
        User d = this.userService.deleteUserById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateUser (@RequestBody User user, @PathVariable Long id) {
        User d = this.userService.updateUserById(id, user.getName(), user.getEmail(), user.getPassword(), user.getRole().getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
