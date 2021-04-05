package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Movie;
import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.domain.Actor;
import com.ufes.inf.dwws.umdb.domain.Director;

import com.ufes.inf.dwws.umdb.persistence.MovieRepository;
import com.ufes.inf.dwws.umdb.persistence.GenreRepository;
import com.ufes.inf.dwws.umdb.persistence.ActorRepository;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;


    public MovieService (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(String name, int year, List<Genre> genres, List<Actor> actors, List<Director> directors){
        List<Movie> d = this.movieRepository.findByName(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.movieRepository.save(new Movie(name, year, genres, actors, directors));
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

    public List<Movie> findAllByGenre(Long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);
        if (genre.isPresent()) {
            Genre g = genre.get();
            List<Movie> movies = this.movieRepository.findByGenres(g);
            return movies;
        } else {
            return null;
        }
    }

    public List<Movie> findAllByActor(Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);
        if (actor.isPresent()) {
            Actor a = actor.get();
            List<Movie> movies = this.movieRepository.findByActors(a);
            return movies;
        } else {
            return null;
        }
    }

    public List<Movie> findAllByDirector(Long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            Director d = director.get();
            List<Movie> movies = this.movieRepository.findByDirectors(d);
            return movies;
        } else {
            return null;
        }
    }

}
