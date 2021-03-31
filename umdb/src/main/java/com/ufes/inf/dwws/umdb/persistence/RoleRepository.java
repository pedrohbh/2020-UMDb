package com.ufes.inf.dwws.umdb.persistence;

import com.ufes.inf.dwws.umdb.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaRepository<Role, Long> {
    List<Role> findByNameContaining(@Param("name") String name);
    List<Role> findByName(@Param("name") String name);


}

