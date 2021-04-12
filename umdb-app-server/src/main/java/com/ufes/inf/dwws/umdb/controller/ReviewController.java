package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Review;
import com.ufes.inf.dwws.umdb.domain.User;
import com.ufes.inf.dwws.umdb.service.MovieDTO;
import com.ufes.inf.dwws.umdb.service.MovieService;
import com.ufes.inf.dwws.umdb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ReviewController {

    ReviewService reviewService;

    @Autowired
    MovieService movieService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/api/close/movie/{id}/review")
    @ResponseBody
    public ResponseEntity<Object> saveReview(@PathVariable Long id, @RequestBody Review review, @AuthenticationPrincipal User user){
        MovieDTO movieDTO = this.movieService.newReview(id, review, user);

        if(movieDTO != null){
            return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/open/review/{id}")
    @ResponseBody
    public ResponseEntity<Object> findReview(@PathVariable Long id) {
        Review d = this.reviewService.findReviewById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/close/review/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteReview (@PathVariable Long id, @AuthenticationPrincipal User userDetails) {
        Review review = this.reviewService.findReviewById(id);
        if (userDetails.getRole().getName().equals("ROLE_ADMIN") || userDetails.getId().equals(review.getUser().getId())) {
            Boolean isDeleted = this.reviewService.deleteReviewById(id);
            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Internal server error", HttpStatus.BAD_REQUEST);
        }

    }
}
