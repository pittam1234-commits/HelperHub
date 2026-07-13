package com.helperhub.controller;

import com.helperhub.entity.Rating;
import com.helperhub.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin("*")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating saveRating(
            @RequestBody Rating rating) {

        return ratingService.saveRating(rating);
    }

    @GetMapping
    public List<Rating> getAllRatings() {

        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public Rating getRating(
            @PathVariable Long id) {

        return ratingService.getRating(id);
    }

    @GetMapping("/worker/{workerId}")
    public List<Rating> getRatingsByWorker(
            @PathVariable Long workerId) {

        return ratingService
                .getRatingsByWorker(workerId);
    }

    @GetMapping("/average/{workerId}")
    public double getAverageRating(
            @PathVariable Long workerId) {

        return ratingService
                .getAverageRating(workerId);
    }

    @GetMapping("/booking/{bookingId}/exists")
    public boolean reviewExists(
            @PathVariable Long bookingId) {

        return ratingService
                .reviewExists(bookingId);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(
            @PathVariable Long id) {

        ratingService.deleteRating(id);
    }
}
