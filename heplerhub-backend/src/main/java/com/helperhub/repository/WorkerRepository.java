package com.helperhub.repository;

import com.helperhub.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository
        extends JpaRepository<Worker, Long> {

    Optional<Worker> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Worker> findByNameContainingIgnoreCase(
            String name
    );

    List<Worker> findByCategoryIgnoreCase(
            String category
    );

    List<Worker> findByCityIgnoreCase(
            String city
    );

    List<Worker>
    findByCategoryIgnoreCaseAndCityIgnoreCase(
            String category,
            String city
    );

    List<Worker>
    findByExperienceGreaterThanEqual(
            int experience
    );

    List<Worker> findByPricePerHourBetween(
            double minPrice,
            double maxPrice
    );

    List<Worker>
    findByPricePerHourLessThanEqual(
            double price
    );

    List<Worker> findByAvailableTrue();

    List<Worker> findByStatus(String status);

    Page<Worker> findAll(Pageable pageable);

    Page<Worker> findByCategoryIgnoreCase(
            String category,
            Pageable pageable
    );
}