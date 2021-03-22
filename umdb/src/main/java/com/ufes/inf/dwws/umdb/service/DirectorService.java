package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    public DirectorService (DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    public Director saveDirector(String name){
        return this.directorRepository.save(new Director(name));
    }

    public List<Director> findAll(){
        return  this.directorRepository.findAll();
    }

}
