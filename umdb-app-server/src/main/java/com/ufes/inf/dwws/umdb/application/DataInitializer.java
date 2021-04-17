package com.ufes.inf.dwws.umdb.application;

import com.ufes.inf.dwws.umdb.domain.*;
import com.ufes.inf.dwws.umdb.persistence.*;
import com.ufes.inf.dwws.umdb.service.ActorService;
import com.ufes.inf.dwws.umdb.service.DirectorService;
import com.ufes.inf.dwws.umdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MovieRepository movieRepository;




    @Override
    public void run(String... args) throws Exception {
        //create roles

        System.out.println("Creating default roles....");
        if (this.roleRepository.findByName("ROLE_USER") == null){
            this.roleRepository.save(new Role("ROLE_USER"));
        }

        if (this.roleRepository.findByName("ROLE_ADMIN") == null){
            this.roleRepository.save(new Role("ROLE_ADMIN"));
        }

        System.out.println("Creating default users....");
        //create users
        if (this.userRepository.findByEmail("admindefault@email") == null){

            this.userRepository.save(new User(
                            "Maurilio",
                            "admindefault@email",
                            userService.passwordEncoder.encode("senha123"),
                            roleRepository.findByName("ROLE_ADMIN")
                    )
            );
        }
        if (this.userRepository.findByEmail("userdefault@email") == null){

            this.userRepository.save(new User(
                            "Julinho da Van",
                            "userdefault@email",
                            userService.passwordEncoder.encode("senha123"),
                            roleRepository.findByName("ROLE_USER")
                    )
            );
        }

        // create actor
        System.out.println("Creating default actor....");
        if(this.actorRepository.findByName("Rogerinho do Inga").size() == 0){
            this.actorRepository.save(new Actor("Rogerinho do Inga"));
        }

        // create director
        System.out.println("Creating default director....");
        if(this.directorRepository.findByName("Renan").size() == 0){

            this.directorRepository.save(new Director("Renan"));
        }

        // create genre
        System.out.println("Creating default genre....");
        if(this.genreRepository.findByName("transporte alternativo").size() == 0){
            this.genreRepository.save(new Genre("transporte alternativo"));
        }

        // create review
        //FIXME: não sei pq não funciona
//        // create movie0
//        System.out.println("Creating default movie....");
//        if(this.movieRepository.findByName("80 filmes para ver enquanto dirige").size() == 0){
//            System.out.println("helloooo");
//            List<Actor> actors = this.actorRepository.findAll();
//            List<Genre> genres = this.genreRepository.findAll();
//            List<Director> directors = this.directorRepository.findAll();
//
//
//            this.movieRepository.save(new Movie(
//                    "80 filmes para ver enquanto dirige",
//                    2019,
//                    genres,
//                    actors,
//                    directors
//            ));
//
//            System.out.println("Creating default review....");
//            Review review = new Review();
//            review.setRating(4);
//            review.setCommentary("ótimo filmes para não dormir no volante");
//            review.setUser(this.userRepository.findByEmail("userdefault@email"));
//            review.setMovie(this.movieRepository.findByName("80 filmes para ver enquanto dirige").get(0));
//            this.reviewRepository.save(review);
//        }
    }
}
