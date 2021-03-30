package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.persistence.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public GenreService (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public Genre saveGenre(String name){
        List<Genre> d = this.genreRepository.findByName(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.genreRepository.save(new Genre(name));
        }
    }

    public List<Genre> findAll(){
        return  this.genreRepository.findAll();
    }

    public Genre findGenreById (Long id) {
        Optional<Genre> d = this.genreRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Genre deleteGenreById(Long id) {
        Optional<Genre> d = this.genreRepository.findById(id);

        if (d.isPresent()) {
            this.genreRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public Genre updateGenreById(Long id, String name) {
        Optional<Genre> d = this.genreRepository.findById(id);

        if (d.isPresent()) {
            d.get().setName(name);
            this.genreRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
