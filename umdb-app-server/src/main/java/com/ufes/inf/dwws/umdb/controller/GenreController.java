package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.service.GenreDTO;
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

    @PostMapping("/api/admin/genre")
    @ResponseBody
    public ResponseEntity<Object> saveGenre (@RequestBody Genre genre) {
        GenreDTO genreDTO = this.genreService.saveGenre(genre.getName());

        if (genreDTO != null) {
            return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Já existe um genero cadastrado com esse nome!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/genre")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.genreService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/open/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> findGenre(@PathVariable Long id) {
        GenreDTO genreDTO = this.genreService.findGenreById(id);

        if (genreDTO != null) {
            return new ResponseEntity<>(genreDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/admin/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteGenre (@PathVariable Long id) {
        Boolean isDeleted = this.genreService.deleteGenreById(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/admin/genre/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateGenre (@RequestBody Genre genre, @PathVariable Long id) {
        GenreDTO genreDTO = this.genreService.updateGenreById(id, genre.getName());

        if (genreDTO != null) {
            return new ResponseEntity<>(genreDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
