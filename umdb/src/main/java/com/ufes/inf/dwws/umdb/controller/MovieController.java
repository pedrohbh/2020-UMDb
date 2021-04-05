package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Movie;
import com.ufes.inf.dwws.umdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("/api/admin/movie")
    @ResponseBody
    public ResponseEntity<Object> saveMovie (@RequestBody Movie movie) {
        Movie d = this.movieService.saveMovie(movie.getName(), movie.getYear(), movie.getGenres(), movie.getActors(), movie.getDirectors());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um filme cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.movieService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/open/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMovie(@PathVariable Long id) {
        Movie d = this.movieService.findMovieById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteMovie (@PathVariable Long id) {
        Movie d = this.movieService.deleteMovieById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateMovie (@RequestBody Movie movie, @PathVariable Long id) {
        Movie d = this.movieService.updateMovieById(id, movie.getName(), movie.getYear());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyGenre(@PathVariable Long id) {
        List<Movie> m = this.movieService.findAllByGenre(id);

        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyActor(@PathVariable Long id) {
        List<Movie> m = this.movieService.findAllByActor(id);

        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyDirector(@PathVariable Long id) {
        List<Movie> m = this.movieService.findAllByDirector(id);

        if (m != null) {
            return new ResponseEntity<>(m, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
}
