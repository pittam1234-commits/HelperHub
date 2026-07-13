package com.helperhub.controller;

import com.helperhub.entity.Booking;
import com.helperhub.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping
    public Booking save(
            @RequestBody Booking booking) {

        return service.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAll() {

        return service.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getById(
            @PathVariable Long id) {

        return service.getBooking(id);
    }

    @PutMapping("/{id}")
    public Booking update(
            @PathVariable Long id,
            @RequestBody Booking booking) {

        return service.updateBooking(
                id,
                booking
        );
    }

    @PutMapping("/{id}/approve")
    public Booking approveBooking(
            @PathVariable Long id) {

        return service.approveBooking(id);
    }

    @PutMapping("/{id}/reject")
    public Booking rejectBooking(
            @PathVariable Long id) {

        return service.rejectBooking(id);
    }

    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(
            @PathVariable Long id) {

        return service.cancelBooking(id);
    }

    @PutMapping("/{id}/complete")
    public Booking completeBooking(
            @PathVariable Long id) {

        return service.completeBooking(id);
    }

    @GetMapping("/worker/{workerId}")
    public List<Booking> getBookingsByWorker(
            @PathVariable Long workerId) {

        return service.getBookingsByWorker(
                workerId
        );
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(
            @PathVariable Long userId) {

        return service.getBookingsByUser(
                userId
        );
    }

    @GetMapping("/status/{status}")
    public List<Booking> getBookingsByStatus(
            @PathVariable String status) {

        return service.getBookingsByStatus(
                status
        );
    }

    @GetMapping(
            "/worker/{workerId}/status/{status}"
    )
    public List<Booking>
    getBookingsByWorkerAndStatus(
            @PathVariable Long workerId,
            @PathVariable String status) {

        return service
                .getBookingsByWorkerAndStatus(
                        workerId,
                        status
                );
    }

    @GetMapping(
            "/user/{userId}/status/{status}"
    )
    public List<Booking>
    getBookingsByUserAndStatus(
            @PathVariable Long userId,
            @PathVariable String status) {

        return service
                .getBookingsByUserAndStatus(
                        userId,
                        status
                );
    }
}
