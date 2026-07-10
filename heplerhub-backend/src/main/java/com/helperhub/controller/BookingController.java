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

    // Create Booking
    @PostMapping
    public Booking save(@RequestBody Booking booking) {
        return service.saveBooking(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAll() {
        return service.getAllBookings();
    }

    // Get Booking By ID
    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id) {
        return service.getBooking(id);
    }

    // Update Booking
    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id,
                          @RequestBody Booking booking) {
        return service.updateBooking(id, booking);
    }

    // Approve Booking
    @PutMapping("/{id}/approve")
    public Booking approveBooking(@PathVariable Long id) {
        return service.approveBooking(id);
    }

    // Reject Booking
    @PutMapping("/{id}/reject")
    public Booking rejectBooking(@PathVariable Long id) {
        return service.rejectBooking(id);
    }

    // Delete Booking
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteBooking(id);
        return "Booking Cancelled Successfully";
    }
}