package com.helperhub.controller;

import com.helperhub.entity.Rating;
import com.helperhub.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin(origins = "*")
public class RatingController {

    @Autowired
    private RatingService service;

    // Add Rating
    @PostMapping
    public Rating save(@RequestBody Rating rating) {
        return service.saveRating(rating);
    }

    // Get All Ratings
    @GetMapping
    public List<Rating> getAll() {
        return service.getAllRatings();
    }

    // Get Rating By Id
    @GetMapping("/{id}")
    public Rating getById(@PathVariable Long id) {
        return service.getRating(id);
    }

    // Get Ratings Of One Worker
    @GetMapping("/worker/{workerId}")
    public List<Rating> getWorkerRatings(@PathVariable Long workerId) {
        return service.getRatingsByWorker(workerId);
    }

    // Average Rating
    @GetMapping("/average/{workerId}")
    public double getAverageRating(@PathVariable Long workerId) {
        return service.getAverageRating(workerId);
    }

    // Delete Rating
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteRating(id);
        return "Rating Deleted Successfully";
    }
}