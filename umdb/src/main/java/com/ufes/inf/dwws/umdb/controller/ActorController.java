package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ActorController {

    ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @PostMapping("/api/actor")
    @ResponseBody
    public ResponseEntity<Object> saveActor (@RequestBody Actor actor) {
        Actor d = this.actorService.saveActor(actor.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um ator cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/actor")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.actorService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> findActor(@PathVariable Long id) {
        Actor d = this.actorService.findActorById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteActor (@PathVariable Long id) {
        Actor d = this.actorService.deleteActorById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateActor (@RequestBody Actor actor, @PathVariable Long id) {
        Actor d = this.actorService.updateActorById(id, actor.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
