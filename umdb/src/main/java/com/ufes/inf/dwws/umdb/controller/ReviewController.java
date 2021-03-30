package com.ufes.inf.dwws.umdb.controller;

import com.ufes.inf.dwws.umdb.domain.Review;
import com.ufes.inf.dwws.umdb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/api/review")
    @ResponseBody
    public ResponseEntity<Object> saveReview (@RequestBody Review review) {
        Review d = this.reviewService.saveReview(review.getRating(), review.getCommentary());

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("O rating tem que vir no range 0 <= rating <= 5", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/review")
    @ResponseBody
    public ResponseEntity<List> findAll () {
        return new ResponseEntity<>(this.reviewService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/api/review/{id}")
    @ResponseBody
    public ResponseEntity<Object> findReview(@PathVariable Long id) {
        Review d = this.reviewService.findReviewById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/review/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteReview (@PathVariable Long id) {
        Review d = this.reviewService.deleteReviewById(id);

        if (d != null) {
            return new ResponseEntity<>(d, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
