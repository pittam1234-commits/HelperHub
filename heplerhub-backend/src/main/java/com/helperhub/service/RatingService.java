package com.helperhub.service;

import com.helperhub.entity.Rating;
import com.helperhub.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    // Add Rating
    public Rating saveRating(Rating rating) {
        return repository.save(rating);
    }

    // Get All Ratings
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    // Get Rating By Id
    public Rating getRating(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Get Ratings By Worker
    public List<Rating> getRatingsByWorker(Long workerId) {
        return repository.findByWorkerId(workerId);
    }

    // Delete Rating
    public void deleteRating(Long id) {
        repository.deleteById(id);
    }

    // Average Rating
    public double getAverageRating(Long workerId) {

        List<Rating> ratings = repository.findByWorkerId(workerId);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Rating rating : ratings) {
            total += rating.getStars();
        }

        return total / ratings.size();
    }
}