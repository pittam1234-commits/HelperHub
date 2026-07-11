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

    @Autowired
    private EmailService emailService;

    // Save Booking
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
                .orElseThrow(
                        () -> new RuntimeException("User Not Found")
                );

        Worker worker = workerRepository
                .findById(booking.getWorker().getId())
                .orElseThrow(
                        () -> new RuntimeException("Worker Not Found")
                );

        booking.setUser(user);
        booking.setWorker(worker);
        booking.setStatus("PENDING");

        Booking savedBooking = repository.save(booking);

        try {

            String subject =
                    "Booking Confirmation - HelperHub";

            String body =
                    "Hello " + user.getName() + ",\n\n" +
                    "Your booking has been placed successfully.\n\n" +
                    "Worker Name : " + worker.getName() + "\n" +
                    "Category : " + worker.getCategory() + "\n" +
                    "City : " + worker.getCity() + "\n" +
                    "Booking Date : " +
                    savedBooking.getBookingDate() + "\n" +
                    "Booking Time : " +
                    savedBooking.getBookingTime() + "\n" +
                    "Status : " +
                    savedBooking.getStatus() + "\n\n" +
                    "Thank you for choosing HelperHub.";

            emailService.sendEmail(
                    user.getEmail(),
                    subject,
                    body
            );

        } catch (Exception e) {

            System.out.println(
                    "Booking saved but email failed: "
                            + e.getMessage()
            );
        }

        return savedBooking;
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {

        return repository.findAll();
    }

    // Get Booking By ID
    public Booking getBooking(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Booking Not Found"
                        )
                );
    }

    // Update Booking
    public Booking updateBooking(
            Long id,
            Booking booking) {

        Booking existing = repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Booking Not Found"
                        )
                );

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

    // Approve Booking
    public Booking approveBooking(Long id) {

        Booking booking = repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Booking Not Found"
                        )
                );

        booking.setStatus("APPROVED");

        Booking updatedBooking =
                repository.save(booking);

        User user = updatedBooking.getUser();

        try {

            emailService.sendEmail(
                    user.getEmail(),
                    "Booking Approved",
                    "Hello " + user.getName()
                            + ",\n\nYour booking has been APPROVED."
                            + "\n\nThank you for choosing HelperHub."
            );

        } catch (Exception e) {

            System.out.println(
                    "Approval email failed: "
                            + e.getMessage()
            );
        }

        return updatedBooking;
    }

    // Reject Booking
    public Booking rejectBooking(Long id) {

        Booking booking = repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Booking Not Found"
                        )
                );

        booking.setStatus("REJECTED");

        Booking updatedBooking =
                repository.save(booking);

        User user = updatedBooking.getUser();

        try {

            emailService.sendEmail(
                    user.getEmail(),
                    "Booking Rejected",
                    "Hello " + user.getName()
                            + ",\n\nSorry, your booking has been REJECTED."
                            + "\nPlease book another worker."
            );

        } catch (Exception e) {

            System.out.println(
                    "Rejection email failed: "
                            + e.getMessage()
            );
        }

        return updatedBooking;
    }

    // Delete Booking
    public void deleteBooking(Long id) {

        if (!repository.existsById(id)) {

            throw new RuntimeException(
                    "Booking Not Found"
            );
        }

        repository.deleteById(id);
    }
}
