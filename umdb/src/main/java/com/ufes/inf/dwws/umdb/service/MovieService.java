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

import java.util.LinkedList;
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

    public Movie saveMovie(Movie movie){

        List<Actor> actors = new LinkedList<>();
        List<Genre> genres = new LinkedList<>();
        List<Director> directors = new LinkedList<>();

        movie.getActors().forEach(actor -> {actors.add(actorRepository.findById(actor.getId()).orElse(null));});
        movie.getGenres().forEach(genre -> {genres.add(genreRepository.findById(genre.getId()).orElse(null));});
        movie.getDirectors().forEach(director -> {directors.add(directorRepository.findById(director.getId()).orElse(null));});

        List<Movie> d = this.movieRepository.findByName(movie.getName());

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.movieRepository.save(new Movie(movie.getName(), movie.getYear(), genres, actors, directors));
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

    public Movie updateMovieById(Long id, Movie movieToUpdate) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {

            List<Actor> actors = new LinkedList<>();
            List<Genre> genres = new LinkedList<>();
            List<Director> directors = new LinkedList<>();

            movieToUpdate.getActors().forEach(actor -> {actors.add(actorRepository.findById(actor.getId()).orElse(null));});
            movieToUpdate.getGenres().forEach(genre -> {genres.add(genreRepository.findById(genre.getId()).orElse(null));});
            movieToUpdate.getDirectors().forEach(director -> {directors.add(directorRepository.findById(director.getId()).orElse(null));});


            Movie m = movie.get();
            m.setName(movieToUpdate.getName());
            m.setYear(movieToUpdate.getYear());
            m.setActors(actors);
            m.setDirectors(directors);
            m.setGenres(genres);
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
