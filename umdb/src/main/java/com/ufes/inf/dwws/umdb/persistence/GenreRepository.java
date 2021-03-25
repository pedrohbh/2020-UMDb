package com.ufes.inf.dwws.umdb.persistence;

import com.ufes.inf.dwws.umdb.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "genre", path = "genre")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long>, JpaRepository<Genre, Long> {
    List<Genre> findByNameContaining(@Param("name") String name);
    List<Genre> findByName(@Param("name") String name);


}