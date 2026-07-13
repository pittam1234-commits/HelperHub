package com.helperhub.repository;

import com.helperhub.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository
        extends JpaRepository<Rating, Long> {

    List<Rating> findByWorkerId(Long workerId);

    boolean existsByBookingId(Long bookingId);
}
