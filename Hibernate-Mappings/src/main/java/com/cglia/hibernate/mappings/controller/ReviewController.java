package com.cglia.hibernate.mappings.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cglia.hibernate.mappings.entity.Review;
import com.cglia.hibernate.mappings.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/v1.0")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/v1.0/{id}")
    public Review getReviewById(@PathVariable int id) {
        return reviewService.findById(id);
    }

    @PostMapping("/v1.0")
    public void addReview(@RequestBody Review review) {
        reviewService.save(review);
    }

    @PutMapping("/v1.0/{id}")
    public void updateReview(@RequestBody Review review, @PathVariable int id) {
        reviewService.update(review, id);
    }

    @DeleteMapping("/v1.0/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewService.deleteById(id);
    }
}

