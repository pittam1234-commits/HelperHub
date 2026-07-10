package com.helperhub.controller;

import com.helperhub.entity.Payment;
import com.helperhub.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make Payment
    @PostMapping
    public Payment makePayment(@RequestBody Map<String, Object> request) {

        Long bookingId = Long.valueOf(request.get("bookingId").toString());
        double amount = Double.parseDouble(request.get("amount").toString());
        String paymentMethod = request.get("paymentMethod").toString();

        return paymentService.makePayment(
                bookingId,
                amount,
                paymentMethod
        );
    }

    // Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get Payment By Booking
    @GetMapping("/booking/{bookingId}")
    public Payment getPaymentByBooking(@PathVariable Long bookingId) {

        return paymentService.getPaymentByBooking(bookingId);
    }

    // Get Payments By Status
    @GetMapping("/status/{status}")
    public List<Payment> getPaymentsByStatus(
            @PathVariable String status) {

        return paymentService.getPaymentsByStatus(status);
    }

    // Get Payments By Method
    @GetMapping("/method/{method}")
    public List<Payment> getPaymentsByMethod(
            @PathVariable String method) {

        return paymentService.getPaymentsByMethod(method);
    }

    // Update Payment Status
    @PutMapping("/{id}")
    public Payment updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        return paymentService.updatePaymentStatus(
                id,
                request.get("paymentStatus")
        );
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {

        paymentService.deletePayment(id);

        return "Payment Deleted Successfully";
    }

}