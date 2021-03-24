package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class GenreController {

    GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @PostMapping("/api/genre")
    @ResponseBody
    public ResponseEntity<Object> saveGenre (@RequestBody Genre genre) {
        Genre d = this.genreService.saveGenre(genre.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Já existe um genero cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/genre")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.genreService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> findGenre(@PathVariable Long id) {
        Genre d = this.genreService.findGenreById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteGenre (@PathVariable Long id) {
        Genre d = this.genreService.deleteGenreById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateGenre (@RequestBody Genre genre, @PathVariable Long id) {
        Genre d = this.genreService.updateGenreById(id, genre.getName());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
