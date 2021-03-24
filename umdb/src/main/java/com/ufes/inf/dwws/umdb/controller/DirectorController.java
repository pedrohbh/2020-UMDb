package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class DirectorController {

    DirectorService directorService;

    public DirectorController(DirectorService directorService){
        this.directorService = directorService;
    }

    @PostMapping("/api/director")
    @ResponseBody
    public ResponseEntity<Object> saveDirector (@RequestBody Director director) {
        Director d = this.directorService.saveDirector(director.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um diretor cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/director")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.directorService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> findDirector(@PathVariable Long id) {
        Director d = this.directorService.findDirectorById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteDirector (@PathVariable Long id) {
        Director d = this.directorService.deleteDirectorById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateDirector (@RequestBody Director director, @PathVariable Long id) {
        Director d = this.directorService.updateDirectorById(id, director.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
