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

    @PostMapping("/api/movie")
    @ResponseBody
    public ResponseEntity<Object> saveMovie (@RequestBody Movie movie) {
        Movie d = this.movieService.saveMovie(movie.getName(), movie.getYear());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um filme cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/movie")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.movieService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMovie(@PathVariable Long id) {
        Movie d = this.movieService.findMovieById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteMovie (@PathVariable Long id) {
        Movie d = this.movieService.deleteMovieById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateMovie (@RequestBody Movie movie, @PathVariable Long id) {
        Movie d = this.movieService.updateMovieById(id, movie.getName(), movie.getYear());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
