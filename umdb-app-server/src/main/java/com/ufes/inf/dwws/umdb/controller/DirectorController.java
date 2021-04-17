package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.service.DirectorDTO;
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

    @PostMapping("/api/admin/director")
    @ResponseBody
    public ResponseEntity<Object> saveDirector (@RequestBody Director director) {
        DirectorDTO directorDTO = this.directorService.saveDirector(director.getName());

        if (directorDTO != null) {
            return new ResponseEntity<>(directorDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Já existe um diretor cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/director")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.directorService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/open/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> findDirector(@PathVariable Long id) {
        DirectorDTO directorDTO = this.directorService.findDirectorById(id);

        if (directorDTO != null) {
            return new ResponseEntity<>(directorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteDirector (@PathVariable Long id) {
        Boolean isDeleted = this.directorService.deleteDirectorById(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateDirector (@RequestBody Director director, @PathVariable Long id) {
        DirectorDTO directorDTO = this.directorService.updateDirectorById(id, director.getName());

        if (directorDTO != null) {
            return new ResponseEntity<>(directorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
