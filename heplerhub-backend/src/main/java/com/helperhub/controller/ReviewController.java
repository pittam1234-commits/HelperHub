package com.helperhub.controller;

import com.helperhub.entity.Review;
import com.helperhub.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Add Review
    @PostMapping
    public Review addReview(@RequestBody Map<String, Object> request) {

        Long userId = Long.valueOf(request.get("userId").toString());
        Long workerId = Long.valueOf(request.get("workerId").toString());
        int rating = Integer.parseInt(request.get("rating").toString());
        String comment = request.get("comment").toString();

        return reviewService.addReview(userId, workerId, rating, comment);
    }

    // Get All Reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Get Review By ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // Get Reviews By Worker
    @GetMapping("/worker/{workerId}")
    public List<Review> getReviewsByWorker(@PathVariable Long workerId) {
        return reviewService.getReviewsByWorker(workerId);
    }

    // Get Worker Average Rating
    @GetMapping("/average/{workerId}")
    public double getWorkerRating(@PathVariable Long workerId) {
        return reviewService.getWorkerRating(workerId);
    }

    // Delete Review
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review Deleted Successfully";
    }

}