package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    public DirectorService (DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    public Director saveDirector(String name){
        List<Director> d = this.directorRepository.findByNameContaining(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.directorRepository.save(new Director(name));
        }
    }

    public List<Director> findAll(){
        return  this.directorRepository.findAll();
    }

    public Director findDirectorById (Long id) {
        Optional<Director> d = this.directorRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Director deleteDirectorById(Long id) {
        Optional<Director> d = this.directorRepository.findById(id);

        if (d.isPresent()) {
            this.directorRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public Director updateDirectorById(Long id, String name) {
        Optional<Director> d = this.directorRepository.findById(id);

        if (d.isPresent()) {
            d.get().setName(name);
            this.directorRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
