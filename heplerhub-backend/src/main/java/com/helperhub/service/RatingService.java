package com.helperhub.service;

import com.helperhub.entity.Booking;
import com.helperhub.entity.Rating;
import com.helperhub.repository.BookingRepository;
import com.helperhub.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private BookingRepository bookingRepository;

    public Rating saveRating(Rating rating) {

        if (rating.getBooking() == null ||
                rating.getBooking().getId() == null) {

            throw new RuntimeException(
                    "Booking ID is required"
            );
        }

        Long bookingId =
                rating.getBooking().getId();

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Booking Not Found"
                        ));

        if (!"COMPLETED".equalsIgnoreCase(
                booking.getStatus())) {

            throw new RuntimeException(
                    "Review allowed only for COMPLETED booking"
            );
        }

        if (repository.existsByBookingId(bookingId)) {

            throw new RuntimeException(
                    "Review already submitted for this booking"
            );
        }

        if (rating.getStars() < 1 ||
                rating.getStars() > 5) {

            throw new RuntimeException(
                    "Rating must be between 1 and 5"
            );
        }

        rating.setUser(booking.getUser());
        rating.setWorker(booking.getWorker());
        rating.setBooking(booking);

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

    public boolean reviewExists(Long bookingId) {

        return repository.existsByBookingId(
                bookingId
        );
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
