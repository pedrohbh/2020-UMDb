package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleService (RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role saveRole(String name){
        List<Role> d = this.roleRepository.findByName(name);

        if (!d.isEmpty()) {
            return null;
        } else {
            return this.roleRepository.save(new Role(name));
        }
    }

    public List<Role> findAll(){
        return  this.roleRepository.findAll();
    }

    public Role findRoleById (Long id) {
        Optional<Role> d = this.roleRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Role deleteRoleById(Long id) {
        Optional<Role> d = this.roleRepository.findById(id);

        if (d.isPresent()) {
            this.roleRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

    public Role updateRoleById(Long id, String name) {
        Optional<Role> d = this.roleRepository.findById(id);

        if (d.isPresent()) {
            d.get().setName(name);
            this.roleRepository.save(d.get());
            return d.get();
        } else {
            return null;
        }
    }

}
