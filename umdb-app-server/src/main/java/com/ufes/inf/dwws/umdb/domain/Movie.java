package com.ufes.inf.dwws.umdb.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = false)
    private int year;
    @Column(nullable = true, unique = false)
    private float averageRating;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Genre",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Genre_id")
    )
    private List<Genre> genres;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Actor_id")
    )
    private List<Actor> actors;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Director",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Director_id")
    )
    private List<Director> directors;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Review> reviews;


    public Movie(String name, int year, List<Genre> genres, List<Actor> actors, List<Director> directors) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.actors = actors;
        this.directors = directors;
    }
    public Movie() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    public float getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
        return actors;
    }
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }
    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }
}