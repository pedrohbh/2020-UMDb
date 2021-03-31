package com.ufes.inf.dwws.umdb.domain;
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

    @ManyToMany
    @JoinTable(
            name = "Movie_Genre",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Genre_id")
    )
    private List<Genre> genreList;
    @ManyToMany
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Actor_id")
    )
    private List<Actor> actorList;
    @ManyToMany
    @JoinTable(
            name = "Movie_Director",
            joinColumns = @JoinColumn(name = "Movie_id"),
            inverseJoinColumns = @JoinColumn(name = "Director_id")
    )
    private List<Director> directorList;
    @OneToMany
    private List<Review> reviewList;


    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
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

    public List<Genre> getGenreList() {
        return genreList;
    }
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Actor> getActorList() {
        return actorList;
    }
    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public List<Director> getDirectorList() {
        return directorList;
    }
    public void setDirectorList(List<Director> directorList) {
        this.directorList = directorList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }
    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}