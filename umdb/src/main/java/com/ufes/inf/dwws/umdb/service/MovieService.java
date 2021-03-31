package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Movie;
import com.ufes.inf.dwws.umdb.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(String name, int year){
        List<Movie> d = this.movieRepository.findByName(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.movieRepository.save(new Movie(name, year));
        }
    }

    public List<Movie> findAll(){
        return  this.movieRepository.findAll();
    }

    public Movie findMovieById (Long id) {
        Optional<Movie> d = this.movieRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Movie deleteMovieById(Long id) {
        Optional<Movie> d = this.movieRepository.findById(id);

        if (d.isPresent()) {
            this.movieRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public Movie updateMovieById(Long id, String name, int year) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {
            Movie m = movie.get();
            m.setName(name);
            m.setYear(year);

            this.movieRepository.save(m);
            return m;
        } else {
            return null;
        }
    }

}
