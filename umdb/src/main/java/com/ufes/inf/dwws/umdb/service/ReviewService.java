package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Review;
import com.ufes.inf.dwws.umdb.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.domain.Movie;

import com.ufes.inf.dwws.umdb.persistence.UserRepository;
import com.ufes.inf.dwws.umdb.persistence.MovieRepository;

import java.util.Optional;
import java.util.List;

@Component
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;

    public ReviewService (ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review){
        User user = userRepository.findById(review.getUser().getId()).get();
        Movie movie = movieRepository.findById(review.getMovie().getId()).get();

        if (review.getRating() >= 0  && review.getRating() <= 5){
            return this.reviewRepository.save(new Review(review.getRating(), review.getCommentary(), user, movie));
        } else {
            return null;
        }
    }

    public List<Review> findAll(){
        return  this.reviewRepository.findAll();
    }

    public Review findReviewById (Long id) {
        Optional<Review> d = this.reviewRepository.findById(id);

        if (d.isPresent()) {
            return d.get();
        } else {
            return null;
        }
    }

    public Review deleteReviewById(Long id) {
        Optional<Review> d = this.reviewRepository.findById(id);

        if (d.isPresent()) {
            this.reviewRepository.deleteById(id);
            return d.get();
        } else {
            return null;
        }
    }

}
