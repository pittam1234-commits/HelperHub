package com.helperhub.repository;

import com.helperhub.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get all bookings of a particular worker
    List<Booking> findByWorkerId(Long workerId);

    // Get all bookings of a particular user
    List<Booking> findByUserId(Long userId);

    // Get bookings by status
    List<Booking> findByStatus(String status);

    // Get bookings of a worker by status
    List<Booking> findByWorkerIdAndStatus(Long workerId, String status);

    // Get bookings of a user by status
    List<Booking> findByUserIdAndStatus(Long userId, String status);

}
