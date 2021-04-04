package com.ufes.inf.dwws.umdb.application;

import com.ufes.inf.dwws.umdb.domain.Role;
import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.persistence.RoleRepository;
import com.ufes.inf.dwws.umdb.persistence.UserRepository;
import com.ufes.inf.dwws.umdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;


    @Override
    public void run(String... args) throws Exception {
        //create roles

        if (this.roleRepository.findByName("ROLE_USER") == null){
            this.roleRepository.save(new Role("ROLE_USER"));
        }

        if (this.roleRepository.findByName("ROLE_ADMIN") == null){
            this.roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (this.users.findByEmail("adminDefault@email") == null){

            this.users.save(new User(
                            "admin default",
                            "adminDefault@email",
                            userService.passwordEncoder.encode("senha123"),
                            roleRepository.findByName("ROLE_ADMIN")
                    )
            );
        }
        if (this.users.findByEmail("userDefault@email") == null){

            this.users.save(new User(
                            "user default",
                            "userDefault@email",
                            userService.passwordEncoder.encode("senha123"),
                            roleRepository.findByName("ROLE_USER")
                    )
            );
        }
        System.out.println("Creating default roles and users if does not exist....");
    }
}
