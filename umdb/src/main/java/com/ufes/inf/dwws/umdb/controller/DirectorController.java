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
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @GetMapping("/api/director")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.directorService.findAll(),HttpStatus.OK);
    }
}
