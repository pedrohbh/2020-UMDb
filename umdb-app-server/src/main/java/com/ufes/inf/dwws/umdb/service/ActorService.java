package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.persistence.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.List;

@Component
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public ActorService (ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public ActorDTO saveActor(String name){
        List<Actor> actors = this.actorRepository.findByName(name);

        if (!actors.isEmpty()) {
            return null;
        } else {
            Actor actor = this.actorRepository.save(new Actor(name));
            return new ActorDTO(actor);
        }
    }

    public List<ActorDTO> findAll(){
        List<ActorDTO> actorsDTO = new LinkedList<>();
        List<Actor> actors =   this.actorRepository.findAll();
        actors.forEach(actor -> {actorsDTO.add(new ActorDTO(actor));});
        return actorsDTO;
    }

    public ActorDTO findActorById (Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);

        if (actor.isPresent()) {
            return new ActorDTO(actor.get());
        } else {
            return null;
        }
    }

    public Boolean deleteActorById(Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);

        if (actor.isPresent()) {
            this.actorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ActorDTO updateActorById(Long id, String name) {
        Optional<Actor> actor = this.actorRepository.findById(id);

        if (actor.isPresent()) {
            actor.get().setName(name);
            this.actorRepository.save(actor.get());
            return new ActorDTO(actor.get());
        } else {
            return null;
        }
    }

}
