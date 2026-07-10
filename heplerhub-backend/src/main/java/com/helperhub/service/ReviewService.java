package com.helperhub.service;

import com.helperhub.entity.Review;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.repository.ReviewRepository;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    // Save Review
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Add Review
    public Review addReview(Long userId,
                            Long workerId,
                            int rating,
                            String comment) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker Not Found"));

        Review review = new Review();
        review.setUser(user);
        review.setWorker(worker);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    // Get All Reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get Review By Id
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review Not Found"));
    }

    // Delete Review
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    // Get Reviews By Worker
    public List<Review> getReviewsByWorker(Long workerId) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker Not Found"));

        return reviewRepository.findByWorker(worker);
    }

    // Get Average Rating
    public double getWorkerRating(Long workerId) {

        List<Review> reviews = getReviewsByWorker(workerId);

        if (reviews.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Review review : reviews) {
            total += review.getRating();
        }

        return total / reviews.size();
    }
}