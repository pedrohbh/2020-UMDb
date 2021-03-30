package com.ufes.inf.dwws.umdb.persistence;

import com.ufes.inf.dwws.umdb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    List<User> findByNameContaining(@Param("name") String name);
    List<User> findByName(@Param("name") String name);
    List<User> findByEmail(@Param("email") String email);


}

