package com.ufes.inf.dwws.umdb.persistence;

import com.ufes.inf.dwws.umdb.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "director", path = "director")
public interface DirectorRepository extends PagingAndSortingRepository<Director, Long>, JpaRepository<Director, Long> {
    List<Director> findByNameContaining(@Param("name") String name);
    List<Director> findByName(@Param("name") String name);


}

