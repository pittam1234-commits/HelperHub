package com.helperhub.service;

import com.helperhub.entity.Booking;
import com.helperhub.entity.User;
import com.helperhub.entity.Worker;
import com.helperhub.repository.BookingRepository;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public Booking saveBooking(Booking booking) {

        if (booking.getUser() == null ||
                booking.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        if (booking.getWorker() == null ||
                booking.getWorker().getId() == null) {
            throw new RuntimeException("Worker ID is required");
        }

        User user = userRepository
                .findById(booking.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        Worker worker = workerRepository
                .findById(booking.getWorker().getId())
                .orElseThrow(() ->
                        new RuntimeException("Worker Not Found"));

        booking.setUser(user);
        booking.setWorker(worker);
        booking.setStatus("PENDING");

        return repository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Booking getBooking(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));
    }

    public Booking updateBooking(
            Long id,
            Booking booking) {

        Booking existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        existing.setBookingDate(
                booking.getBookingDate()
        );

        existing.setBookingTime(
                booking.getBookingTime()
        );

        existing.setStatus(
                booking.getStatus()
        );

        return repository.save(existing);
    }

    public Booking approveBooking(Long id) {

        Booking booking = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        booking.setStatus("APPROVED");

        return repository.save(booking);
    }

    public Booking rejectBooking(Long id) {

        Booking booking = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        booking.setStatus("REJECTED");

        return repository.save(booking);
    }

    public Booking cancelBooking(Long id) {

        Booking booking = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        if (!"PENDING".equalsIgnoreCase(
                booking.getStatus())) {

            throw new RuntimeException(
                    "Only PENDING booking can be cancelled"
            );
        }

        booking.setStatus("CANCELLED");

        return repository.save(booking);
    }

    public Booking completeBooking(Long id) {

        Booking booking = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        if (!"PAID".equalsIgnoreCase(
                booking.getStatus())) {

            throw new RuntimeException(
                    "Only PAID booking can be completed"
            );
        }

        booking.setStatus("COMPLETED");

        return repository.save(booking);
    }

    public List<Booking> getBookingsByWorker(
            Long workerId) {

        return repository.findByWorkerId(workerId);
    }

    public List<Booking> getBookingsByUser(
            Long userId) {

        return repository.findByUserId(userId);
    }

    public List<Booking> getBookingsByStatus(
            String status) {

        return repository.findByStatus(status);
    }

    public List<Booking> getBookingsByWorkerAndStatus(
            Long workerId,
            String status) {

        return repository
                .findByWorkerIdAndStatus(
                        workerId,
                        status
                );
    }

    public List<Booking> getBookingsByUserAndStatus(
            Long userId,
            String status) {

        return repository
                .findByUserIdAndStatus(
                        userId,
                        status
                );
    }

    public void deleteBooking(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Booking Not Found"
            );
        }

        repository.deleteById(id);
    }
}
