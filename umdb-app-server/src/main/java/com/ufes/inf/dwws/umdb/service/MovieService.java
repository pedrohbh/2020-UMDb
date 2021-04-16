package com.ufes.inf.dwws.umdb.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ufes.inf.dwws.umdb.domain.*;

import com.ufes.inf.dwws.umdb.persistence.MovieRepository;
import com.ufes.inf.dwws.umdb.persistence.GenreRepository;
import com.ufes.inf.dwws.umdb.persistence.ActorRepository;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;
import com.ufes.inf.dwws.umdb.persistence.ReviewRepository;


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
    @Autowired
    ReviewRepository reviewRepository;


    public MovieService (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public MovieDTO saveMovie(Movie movie){

        List<Actor> actors = new LinkedList<>();
        List<Genre> genres = new LinkedList<>();
        List<Director> directors = new LinkedList<>();

        movie.getActors().forEach(actor -> {actors.add(actorRepository.findById(actor.getId()).orElse(null));});
        movie.getGenres().forEach(genre -> {genres.add(genreRepository.findById(genre.getId()).orElse(null));});
        movie.getDirectors().forEach(director -> {directors.add(directorRepository.findById(director.getId()).orElse(null));});

        List<Movie> movies = this.movieRepository.findByName(movie.getName());

        if (!movies.isEmpty()) {
            return null;
        } else {
            Movie savedMovie = this.movieRepository.save(new Movie(movie.getName(), movie.getImage(), movie.getSynopsis(), movie.getYear(), genres, actors, directors));
            return initMovieDTO(savedMovie);
        }
    }

    public List<MovieDTO> findAll(){
        List<Movie> movies =  this.movieRepository.findAll();

        List<MovieDTO> moviesDTO = new LinkedList<>();
        movies.forEach( movie -> {
            moviesDTO.add(initMovieDTO(movie));
        });
        return moviesDTO;
    }

    public MovieDTO findMovieById (Long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {
            return initMovieDTO(movie.get());
        } else {
            return null;
        }
    }

    public MovieDTO initMovieDTO(Movie movie){
        List<GenreDTO> genresDTO = new LinkedList<>();
        List<ActorDTO> actorsDTO = new LinkedList<>();
        List<DirectorDTO> directorsDTO = new LinkedList<>();
        List<ReviewDTO> reviewsDTO = new LinkedList<>();

        MovieDTO movieDTO = new MovieDTO(movie);

        if(movie.getActors() != null){
            movie.getActors().forEach(actor -> {actorsDTO.add(new ActorDTO(actor));});
        }

        if(movie.getGenres() != null){
            movie.getGenres().forEach(genre -> {genresDTO.add(new GenreDTO(genre));});
        }

        if(movie.getDirectors() != null){
            movie.getDirectors().forEach(director -> {directorsDTO.add(new DirectorDTO(director));});
        }

        if (movie.getReviews() != null){
            movie.getReviews().forEach(review -> {reviewsDTO.add(new ReviewDTO(review));});
        }


        movieDTO.setActors(actorsDTO);
        movieDTO.setGenres(genresDTO);
        movieDTO.setDirectors(directorsDTO);
        movieDTO.setReviews(reviewsDTO);

        movieDTO.setAvgRate(this.calculateAvgRate(reviewsDTO));
        return movieDTO;
    }

    public double calculateAvgRate(List<ReviewDTO> reviews){
        if (reviews.size() == 0){
            return 0;
        }
        double totalRate = reviews.stream().mapToDouble(ReviewDTO::getRate).sum();
        return totalRate/reviews.size();
    }

    public Boolean deleteMovieById(Long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {
            this.movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public MovieDTO updateMovieById(Long id, Movie movieToUpdate) {
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
            m.setImage(movieToUpdate.getImage());
            m.setSynopsis(movieToUpdate.getSynopsis());
            m.setActors(actors);
            m.setDirectors(directors);
            m.setGenres(genres);

            this.movieRepository.save(m);
            return initMovieDTO(m);
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByGenre(Long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);
        if (genre.isPresent()) {
            Genre g = genre.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByGenres(g);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByActor(Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);
        if (actor.isPresent()) {
            Actor a = actor.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByActors(a);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByDirector(Long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            Director d = director.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByDirectors(d);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public MovieDTO newReview(Long movieId, Review review, User user){
        review.setUser(user);
        Optional<Movie> movie = this.movieRepository.findById(movieId);
        review.setMovie(movie.get());
        this.reviewRepository.save(review);
        movie = this.movieRepository.findById(movieId);
        if(movie.isPresent()){
            return initMovieDTO(movie.get());
        }else{
            return null;
        }
    }
}
