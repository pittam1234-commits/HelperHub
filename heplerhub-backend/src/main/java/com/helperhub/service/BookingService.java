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

        System.out.println("BOOKING REQUEST RECEIVED");

        if (booking.getUser() == null ||
                booking.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        if (booking.getWorker() == null ||
                booking.getWorker().getId() == null) {
            throw new RuntimeException("Worker ID is required");
        }

        System.out.println("USER ID: " + booking.getUser().getId());
        System.out.println("WORKER ID: " + booking.getWorker().getId());

        User user = userRepository
                .findById(booking.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        System.out.println("USER FOUND");

        Worker worker = workerRepository
                .findById(booking.getWorker().getId())
                .orElseThrow(() ->
                        new RuntimeException("Worker Not Found"));

        System.out.println("WORKER FOUND");

        booking.setUser(user);
        booking.setWorker(worker);
        booking.setStatus("PENDING");

        Booking savedBooking = repository.save(booking);

        System.out.println(
                "BOOKING SAVED ID: " + savedBooking.getId()
        );

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Booking getBooking(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));
    }

    public Booking updateBooking(Long id, Booking booking) {

        Booking existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        existing.setBookingDate(booking.getBookingDate());
        existing.setBookingTime(booking.getBookingTime());
        existing.setStatus(booking.getStatus());

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
    }// Get Bookings By Worker
public List<Booking> getBookingsByWorker(Long workerId) {

    return repository.findByWorkerId(workerId);

}

// Get Bookings By User
public List<Booking> getBookingsByUser(Long userId) {

    return repository.findByUserId(userId);

}

// Get Bookings By Status
public List<Booking> getBookingsByStatus(String status) {

    return repository.findByStatus(status);

}

// Get Bookings By Worker And Status
public List<Booking> getBookingsByWorkerAndStatus(
        Long workerId,
        String status) {

    return repository.findByWorkerIdAndStatus(workerId, status);

}

// Get Bookings By User And Status
public List<Booking> getBookingsByUserAndStatus(
        Long userId,
        String status) {

    return repository.findByUserIdAndStatus(userId, status);

}

    public void deleteBooking(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Booking Not Found");
        }

        repository.deleteById(id);
    }public Booking cancelBooking(Long id) {

    Booking booking = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Booking Not Found"));

    booking.setStatus("CANCELLED");

    return repository.save(booking);
}
}
