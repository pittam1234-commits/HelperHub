package com.helperhub.service;

import com.helperhub.entity.Rating;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.repository.RatingRepository;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public Rating saveRating(Rating rating) {

        if (rating.getUser() == null ||
                rating.getUser().getId() == null) {

            throw new RuntimeException(
                    "User ID is required"
            );
        }

        if (rating.getWorker() == null ||
                rating.getWorker().getId() == null) {

            throw new RuntimeException(
                    "Worker ID is required"
            );
        }

        if (rating.getStars() < 1 ||
                rating.getStars() > 5) {

            throw new RuntimeException(
                    "Rating must be between 1 and 5"
            );
        }

        User user = userRepository
                .findById(rating.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User Not Found"
                        ));

        Worker worker = workerRepository
                .findById(rating.getWorker().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Worker Not Found"
                        ));

        rating.setUser(user);
        rating.setWorker(worker);

        return repository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    public Rating getRating(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Rating Not Found"
                        ));
    }

    public List<Rating> getRatingsByWorker(
            Long workerId) {

        return repository.findByWorkerId(workerId);
    }

    public double getAverageRating(Long workerId) {

        List<Rating> ratings =
                repository.findByWorkerId(workerId);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Rating rating : ratings) {
            total += rating.getStars();
        }

        return total / ratings.size();
    }

    public void deleteRating(Long id) {

        if (!repository.existsById(id)) {

            throw new RuntimeException(
                    "Rating Not Found"
            );
        }

        repository.deleteById(id);
    }
}
