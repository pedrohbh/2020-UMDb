package com.ufes.inf.dwws.umdb.service;


import com.ufes.inf.dwws.umdb.domain.Movie;

import java.util.Base64;
import java.util.List;

public class MovieDTO {

    private long id;
    private String name;
    private int year;
    private double avgRate;
    private String image;
    private String synopsis;
    private List<ActorDTO> actors;

    private List<GenreDTO> genres;
    private List<DirectorDTO> directors;
    private List<ReviewDTO> reviews;

    public MovieDTO(Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.year = movie.getYear();
        this.image = movie.getImageAsBase64();
        this.synopsis = movie.getSynopsis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public List<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<ActorDTO> actors) {
        this.actors = actors;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public List<DirectorDTO> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorDTO> directors) {
        this.directors = directors;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
