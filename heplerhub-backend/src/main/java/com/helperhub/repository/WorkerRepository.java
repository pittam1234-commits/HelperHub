package com.helperhub.repository;

import com.helperhub.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    // Search by worker name
    List<Worker> findByNameContainingIgnoreCase(String name);

    // Search by category
    List<Worker> findByCategoryIgnoreCase(String category);

    // Search by city
    List<Worker> findByCityIgnoreCase(String city);

    // Search by category and city
    List<Worker> findByCategoryIgnoreCaseAndCityIgnoreCase(String category, String city);

    // Search by experience
    List<Worker> findByExperienceGreaterThanEqual(int experience);

    // Search by price range
    List<Worker> findByPricePerHourBetween(double minPrice, double maxPrice);

    // Search by maximum price
    List<Worker> findByPricePerHourLessThanEqual(double price);

    // Available workers
    List<Worker> findByAvailableTrue();

    // Search by status
    List<Worker> findByStatus(String status);

    // Pagination
    Page<Worker> findAll(Pageable pageable);

    // Category with Pagination
    Page<Worker> findByCategoryIgnoreCase(String category, Pageable pageable);

}