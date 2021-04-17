package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.service.ActorDTO;
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

    @PostMapping("/api/admin/actor")
    @ResponseBody
    public ResponseEntity<Object> saveActor (@RequestBody Actor actor) {
        ActorDTO actorDTO = this.actorService.saveActor(actor.getName());

        if (actorDTO != null) {
            return new ResponseEntity<>(actorDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Já existe um ator cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/actor")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.actorService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/open/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> findActor(@PathVariable Long id) {
        ActorDTO actor = this.actorService.findActorById(id);

        if (actor != null) {
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteActor (@PathVariable Long id) {
        Boolean isDeleted = this.actorService.deleteActorById(id);

        if (isDeleted != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateActor (@RequestBody Actor actor, @PathVariable Long id) {
        ActorDTO actorDTO = this.actorService.updateActorById(id, actor.getName());

        if (actorDTO != null) {
            return new ResponseEntity<>(actorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
