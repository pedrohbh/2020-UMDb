package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.persistence.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public ActorService (ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public Actor saveActor(String name){
        List<Actor> d = this.actorRepository.findByName(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.actorRepository.save(new Actor(name));
        }
    }

    public List<Actor> findAll(){
        return  this.actorRepository.findAll();
    }

    public Actor findActorById (Long id) {
        Optional<Actor> d = this.actorRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Actor deleteActorById(Long id) {
        Optional<Actor> d = this.actorRepository.findById(id);

        if (d.isPresent()) {
            this.actorRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public Actor updateActorById(Long id, String name) {
        Optional<Actor> d = this.actorRepository.findById(id);

        if (d.isPresent()) {
            d.get().setName(name);
            this.actorRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
