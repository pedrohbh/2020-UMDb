package com.ufes.inf.dwws.umdb.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ufes.inf.dwws.umdb.domain.*;

import com.ufes.inf.dwws.umdb.persistence.MovieRepository;
import com.ufes.inf.dwws.umdb.persistence.GenreRepository;
import com.ufes.inf.dwws.umdb.persistence.ActorRepository;
import com.ufes.inf.dwws.umdb.persistence.DirectorRepository;
import com.ufes.inf.dwws.umdb.persistence.ReviewRepository;
import com.ufes.inf.dwws.umdb.service.DirectorService;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Optional;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    DirectorService directorService;


    public MovieService (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public MovieDTO saveMovie(Movie movie){

        List<Actor> actors = new LinkedList<>();
        List<Genre> genres = new LinkedList<>();
        List<Director> directors = new LinkedList<>();

        movie.getActors().forEach(actor -> {actors.add(actorRepository.findById(actor.getId()).orElse(null));});
        movie.getGenres().forEach(genre -> {genres.add(genreRepository.findById(genre.getId()).orElse(null));});
        movie.getDirectors().forEach(director -> {directors.add(directorRepository.findById(director.getId()).orElse(null));});

        List<Movie> movies = this.movieRepository.findByName(movie.getName());

        if (!movies.isEmpty()) {
            return null;
        } else {
            Movie savedMovie = this.movieRepository.save(new Movie(movie.getName(), movie.getImage(), movie.getSynopsis(), movie.getYear(), genres, actors, directors));
            return initMovieDTO(savedMovie);
        }
    }

    public List<MovieDTO> findAll(){
        List<Movie> movies =  this.movieRepository.findAll();

        List<MovieDTO> moviesDTO = new LinkedList<>();
        movies.forEach( movie -> {
            moviesDTO.add(initMovieDTO(movie));
        });
        return moviesDTO;
    }

    public MovieDTO findMovieById (Long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {
            return initMovieDTO(movie.get());
        } else {
            return null;
        }
    }

    public MovieDTO initMovieDTO(Movie movie){
        List<GenreDTO> genresDTO = new LinkedList<>();
        List<ActorDTO> actorsDTO = new LinkedList<>();
        List<DirectorDTO> directorsDTO = new LinkedList<>();
        List<ReviewDTO> reviewsDTO = new LinkedList<>();

        MovieDTO movieDTO = new MovieDTO(movie);

        if(movie.getActors() != null){
            movie.getActors().forEach(actor -> {actorsDTO.add(new ActorDTO(actor));});
        }

        if(movie.getGenres() != null){
            movie.getGenres().forEach(genre -> {genresDTO.add(new GenreDTO(genre));});
        }

        if(movie.getDirectors() != null){
            movie.getDirectors().forEach(director -> {directorsDTO.add(new DirectorDTO(director));});
        }

        if (movie.getReviews() != null){
            movie.getReviews().forEach(review -> {reviewsDTO.add(new ReviewDTO(review));});
        }


        movieDTO.setActors(actorsDTO);
        movieDTO.setGenres(genresDTO);
        movieDTO.setDirectors(directorsDTO);
        movieDTO.setReviews(reviewsDTO);

        movieDTO.setAvgRate(this.calculateAvgRate(reviewsDTO));
        return movieDTO;
    }

    public double calculateAvgRate(List<ReviewDTO> reviews){
        if (reviews.size() == 0){
            return 0;
        }
        double totalRate = reviews.stream().mapToDouble(ReviewDTO::getRate).sum();
        return totalRate/reviews.size();
    }

    public Boolean deleteMovieById(Long id) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {
            this.movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public MovieDTO updateMovieById(Long id, Movie movieToUpdate) {
        Optional<Movie> movie = this.movieRepository.findById(id);

        if (movie.isPresent()) {

            List<Actor> actors = new LinkedList<>();
            List<Genre> genres = new LinkedList<>();
            List<Director> directors = new LinkedList<>();

            movieToUpdate.getActors().forEach(actor -> {actors.add(actorRepository.findById(actor.getId()).orElse(null));});
            movieToUpdate.getGenres().forEach(genre -> {genres.add(genreRepository.findById(genre.getId()).orElse(null));});
            movieToUpdate.getDirectors().forEach(director -> {directors.add(directorRepository.findById(director.getId()).orElse(null));});

            Movie m = movie.get();
            m.setName(movieToUpdate.getName());
            m.setYear(movieToUpdate.getYear());
            m.setSynopsis(movieToUpdate.getSynopsis());
            m.setActors(actors);
            m.setDirectors(directors);
            m.setGenres(genres);

            this.movieRepository.save(m);
            return initMovieDTO(m);
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByGenre(Long id) {
        Optional<Genre> genre = this.genreRepository.findById(id);
        if (genre.isPresent()) {
            Genre g = genre.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByGenres(g);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByActor(Long id) {
        Optional<Actor> actor = this.actorRepository.findById(id);
        if (actor.isPresent()) {
            Actor a = actor.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByActors(a);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public List<MovieDTO> findAllByDirector(Long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            Director d = director.get();
            List<MovieDTO> moviesDTO = new LinkedList<>();
            List<Movie> movies = this.movieRepository.findByDirectors(d);
            movies.forEach(movie -> {
                moviesDTO.add(initMovieDTO(movie));
            });

            return moviesDTO;
        } else {
            return null;
        }
    }

    public MovieDTO newReview(Long movieId, Review review, User user){
        review.setUser(user);
        Optional<Movie> movie = this.movieRepository.findById(movieId);
        review.setMovie(movie.get());
        this.reviewRepository.save(review);
        movie = this.movieRepository.findById(movieId);
        if(movie.isPresent()){
            return initMovieDTO(movie.get());
        }else{
            return null;
        }
    }

    public List<MovieDTO> filterMovies(String entity, String name){

        List<MovieDTO> moviesDto = new LinkedList<>();

        if (name.isEmpty()){return moviesDto;}
        
        if(entity.equalsIgnoreCase("actor")){
            List<Actor> actors = this.actorRepository.findByNameContaining(name);
            List<Movie> movies = new LinkedList<>();
            actors.forEach(actor -> {
                movies.addAll(this.movieRepository.findByActors(actor));
            });
            
            movies.forEach(movie -> {
                moviesDto.add(this.initMovieDTO(movie));
            });
            
            return moviesDto;
            
            
        }else if(entity.equalsIgnoreCase(("director"))){

            List<Director> directors = this.directorRepository.findByNameContaining(name);
            List<Movie> movies = new LinkedList<>();
            directors.forEach(director -> {
                movies.addAll(this.movieRepository.findByDirectors(director));
            });

            movies.forEach(movie -> {
                moviesDto.add(this.initMovieDTO(movie));
            });

            return moviesDto;
            
        }else if(entity.equalsIgnoreCase(("genre"))){
            List<Genre> genres = this.genreRepository.findByNameContaining(name);
            List<Movie> movies = new LinkedList<>();
            genres.forEach(genre -> {
                movies.addAll(this.movieRepository.findByGenres(genre));
            });

            movies.forEach(movie -> {
                moviesDto.add(this.initMovieDTO(movie));
            });

            return moviesDto;
        }else if(entity.equalsIgnoreCase(("movie"))){
            List<Movie> movies;
            movies = this.movieRepository.findByNameContaining(name);
            movies.forEach(movie -> {
                moviesDto.add(this.initMovieDTO(movie));
            });
            return moviesDto;
        }else{
            return null;
        }

    }

    public MovieDTO getSuggestion(String movieName) {

        String query = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "PREFIX dbp: <http://dbpedia.org/property/>\n" +
        "SELECT ?name ?year ?desc ?director\n"+
        "WHERE {\n"+
        "?film a dbo:Film ; dbp:name " + '"' + movieName + '"' + "@en .\n"+
        "OPTIONAL { ?film rdfs:label ?name . }\n"+
        "OPTIONAL { ?film rdfs:comment ?desc . }\n"+
        "OPTIONAL { ?film dbp:released ?year . }\n"+
        "OPTIONAL { ?film dbo:director ?dir . ?dir dbp:name ?director .}\n"+
        "FILTER(langMatches(lang(?desc), \"EN\"))\n"+
        "FILTER(langMatches(lang(?name), \"EN\"))\n"+
        "}";

        // System.out.println(query);

        QueryExecution queryExecution = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);
        ResultSet results = queryExecution.execSelect();

        if (results.hasNext()) {
            QuerySolution solution = results.next();
            String result = "";
            String name = "";
            String desc = "";
            String directorName = "";
            String yearStr = "";
            Integer year = 0;
            if (solution.contains("name")) {
                name = solution.getLiteral("name").getString();
            }
            if (solution.contains("desc")) {
                desc = solution.getLiteral("desc").getString();
            }
            if (solution.contains("year")) {
                yearStr = solution.getLiteral("year").getString().split("-")[0];
                year = Integer.parseInt(yearStr);
            }
            if (solution.contains("director")) {
                directorName = solution.getLiteral("director").getString();
            }
            
            DirectorDTO directorDTO = directorService.saveDirector(directorName);
            List<Director> directors = directorRepository.findByName(directorName);
            Movie movie = new Movie();
            movie.setId(new Long(-1));
            movie.setName(name);
            movie.setSynopsis(desc);
            movie.setYear(year);
            movie.setDirectors(directors);
            
            
            return initMovieDTO(movie);
        }
        return null;        
    }

    public void publishMovieData() {
        List<Movie> movies = movieRepository.findAll();

        Model model = ModelFactory.createDefaultModel();
        String myNS = "http://localhost:8080/open/movie/data/";
        String dboNS = "http://dbpedia.org/ontology/";
        String dbpNS = "http://dbpedia.org/property/";

        model.setNsPrefix("dbo", dboNS);
        model.setNsPrefix("dbp", dbpNS);

        // Resources
        Resource movieResource = ResourceFactory.createResource(dboNS+ "Film");
        Resource actorResource = ResourceFactory.createResource(dboNS + "Actor");
        Resource directorResource = ResourceFactory.createResource(dboNS + "Director"); // Criado
        Resource genreResource = ResourceFactory.createResource(dboNS + "Genre");
        Resource reviewResource = ResourceFactory.createResource(dboNS + "Review"); // Criado

        // Properties for classes
        Property actors = ResourceFactory.createProperty(dbpNS + "starring");
        Property directors = ResourceFactory.createProperty(dbpNS + "director");
        Property genres = ResourceFactory.createProperty(dbpNS + "genre");
        Property reviews = ResourceFactory.createProperty(dbpNS  + "reviews"); // Criado

        Property released = ResourceFactory.createProperty(dbpNS + "released");
        Property averageRating = ResourceFactory.createProperty(dbpNS + "averageRating");
        Property reviewCommentary = ResourceFactory.createProperty(dbpNS + "reviewCommentary"); // Criado
        Property reviewAuthor = ResourceFactory.createProperty(dbpNS + "reviewAuthor"); // Criado
        Property reviewRating = ResourceFactory.createProperty(dbpNS + "reviewRating"); // Criado

        
        // falta a sinopse, img
        for (Movie m : movies) {
            Resource movieRDF = model.createResource(myNS + m.getId());
            movieRDF.addProperty(RDF.type, movieResource);
            
            // Add nome
            if (m.getName() != null) {
                movieRDF.addProperty(RDFS.label, m.getName());
            }

            // Add year
            if (m.getYear() != 0) {
                movieRDF.addLiteral(released, m.getYear());
            }

            // Add sinopse
            if (m.getSynopsis() != null) {
                movieRDF.addProperty(RDFS.label, m.getSynopsis());
            }

            // Add rating

            // Add actors
            for (Actor a : m.getActors()) {
                movieRDF.addProperty(actors , model.createResource()
                                                   .addProperty(RDF.type, actorResource)
                                                   .addProperty(RDFS.label, a.getName()));
            }

            // Add directors
            for (Director d : m.getDirectors()) {
                movieRDF.addProperty(directors , model.createResource()
                                                   .addProperty(RDF.type, directorResource)
                                                   .addProperty(RDFS.label, d.getName()));
            }
            
            // Add genres
            for (Genre g : m.getGenres()) {
                movieRDF.addProperty(genres , model.createResource()
                                                   .addProperty(RDF.type, genreResource)
                                                   .addProperty(RDFS.label, g.getName()));
            }

            float avgRating = 0;
            // Add reviews
            for (Review r : m.getReviews()) {
                movieRDF.addProperty(reviews , model.createResource()
                                                   .addProperty(RDF.type, reviewResource)
                                                   .addProperty(reviewCommentary, r.getCommentary())
                                                   .addProperty(reviewAuthor, r.getUser().getName())
                                                   .addLiteral(reviewRating, r.getRating()));
                
                avgRating += r.getRating();
            }

            movieRDF.addLiteral(averageRating, avgRating);
        
        }

        PrintStream output = System.out;
        model.write(output, "RDF/XML");
        		
		
		

    }
}
