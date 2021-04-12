package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.persistence.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.List;

@Component
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public GenreService (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public GenreDTO saveGenre(String name){
        List<Genre> genres = this.genreRepository.findByName(name);

        if (!genres.isEmpty()) {
            return null;
        } else {
            Genre genre = this.genreRepository.save(new Genre(name));
            return new GenreDTO(genre);
        }
    }

    public List<GenreDTO> findAll(){
        List<GenreDTO> genresDTO = new LinkedList<>();
        List<Genre> genres =   this.genreRepository.findAll();
        genres.forEach(genre -> {genresDTO.add(new GenreDTO(genre));});
        return genresDTO;
    }

    public GenreDTO findGenreById (Long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);

        if (genre.isPresent()) {
            return new GenreDTO(genre.get());
        } else {
            return null;
        }
    }

    public Boolean deleteGenreById(Long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);

        if (genre.isPresent()) {
            this.genreRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public GenreDTO updateGenreById(Long id, String name) {
        Optional<Genre> genre = this.genreRepository.findById(id);

        if (genre.isPresent()) {
            genre.get().setName(name);
            this.genreRepository.save(genre.get());
            return new GenreDTO(genre.get());
        } else {
            return null;
        }
    }

}
