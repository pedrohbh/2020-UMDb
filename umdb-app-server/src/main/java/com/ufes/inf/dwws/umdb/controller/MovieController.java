package com.ufes.inf.dwws.umdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufes.inf.dwws.umdb.domain.Movie;
import com.ufes.inf.dwws.umdb.service.MovieDTO;
import com.ufes.inf.dwws.umdb.service.MovieService;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("/api/admin/movie")
    @ResponseBody
    public ResponseEntity<Object> saveMovie (@RequestParam String movie, @RequestParam MultipartFile image) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Movie movieObject = objectMapper.readValue(movie, Movie.class);

        movieObject.setImage(image.getBytes());

        MovieDTO movieDTO = this.movieService.saveMovie(movieObject);

        if (movieDTO != null) {
            return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
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
        MovieDTO movieDTO = this.movieService.findMovieById(id);

        if (movieDTO != null) {
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteMovie (@PathVariable Long id) {
        Boolean isDeleted = this.movieService.deleteMovieById(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/movie/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateMovie (@RequestBody Movie movie, @PathVariable Long id) {
        MovieDTO movieDTO = this.movieService.updateMovieById(id, movie);

        if (movieDTO != null) {
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyGenre(@PathVariable Long id) {
        List<MovieDTO> moviesDTO = this.movieService.findAllByGenre(id);

        if (moviesDTO != null) {
            return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/actor/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyActor(@PathVariable Long id) {
        List<MovieDTO> moviesDTO = this.movieService.findAllByActor(id);

        if (moviesDTO != null) {
            return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/movie/director/{id}")
    @ResponseBody
    public ResponseEntity<Object> findMoviebyDirector(@PathVariable Long id) {
        List<MovieDTO> moviesDTO = this.movieService.findAllByDirector(id);

        if (moviesDTO != null) {
            return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
