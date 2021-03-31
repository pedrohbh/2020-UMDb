package com.ufes.inf.dwws.umdb.persistence;

import com.ufes.inf.dwws.umdb.domain.Movie;
import com.ufes.inf.dwws.umdb.domain.Genre;
import com.ufes.inf.dwws.umdb.domain.Director;
import com.ufes.inf.dwws.umdb.domain.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "director", path = "director")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>, JpaRepository<Movie, Long> {
    List<Movie> findByNameContaining(@Param("name") String name);
    List<Movie> findByName(@Param("name") String name);
    List<Movie> findByYear(@Param("year") int year);
//    List<Movie> findByGenre(@Param("genreList") Genre genre);
//    List<Movie> findByDirector(@Param("directorList") Director director);
//    List<Movie> findByActor(@Param("actorList") Actor actor);
}
