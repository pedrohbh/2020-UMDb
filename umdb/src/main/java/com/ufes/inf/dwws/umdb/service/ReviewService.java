package com.ufes.inf.dwws.umdb.service;

import com.ufes.inf.dwws.umdb.domain.Review;
import com.ufes.inf.dwws.umdb.persistence.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewService (ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(int rating, String commentary){
        if (rating >= 0  && rating <= 5){
            return this.reviewRepository.save(new Review(rating, commentary));
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
