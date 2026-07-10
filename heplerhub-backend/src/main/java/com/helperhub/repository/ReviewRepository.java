package com.helperhub.repository;

import com.helperhub.entity.Review;
import com.helperhub.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Get all reviews of a worker
    List<Review> findByWorker(Worker worker);

}