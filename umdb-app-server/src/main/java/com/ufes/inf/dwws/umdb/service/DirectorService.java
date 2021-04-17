package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.List;

@Component
public class DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    public DirectorService (DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    public DirectorDTO saveDirector(String name){
        List<Director> directors = this.directorRepository.findByName(name);

        if (!directors.isEmpty()) {
            return null;
        } else {
            Director director = this.directorRepository.save(new Director(name));
            return new DirectorDTO(director);
        }
    }

    public List<DirectorDTO> findAll() {
        List<DirectorDTO> directorsDTO = new LinkedList<>();
        List<Director> directors =   this.directorRepository.findAll();
        directors.forEach(director -> {directorsDTO.add(new DirectorDTO(director));});
        return directorsDTO;
    }

    public DirectorDTO findDirectorById (Long id) {
        Optional<Director> director = this.directorRepository.findById(id);

        if (director.isPresent()) {
            return new DirectorDTO(director.get());
        } else {
            return null;
        }
    }

    public Boolean deleteDirectorById(Long id) {
        Optional<Director> director = this.directorRepository.findById(id);

        if (director.isPresent()) {
            this.directorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public DirectorDTO updateDirectorById(Long id, String name) {
        Optional<Director> director = this.directorRepository.findById(id);

        if (director.isPresent()) {
            director.get().setName(name);
            this.directorRepository.save(director.get());
            return new DirectorDTO(director.get()) ;
        } else {
            return null;
        }
    }

}
