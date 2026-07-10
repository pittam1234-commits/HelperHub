package com.helperhub.service;

import com.helperhub.entity.Booking;
import com.helperhub.entity.Payment;
import com.helperhub.repository.BookingRepository;
import com.helperhub.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Save Payment (Used by Unit Tests)
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Process Payment
    public Payment makePayment(Long bookingId,
                               double amount,
                               String paymentMethod) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        Payment payment = new Payment();

        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);

        // Generate Transaction ID
        payment.setTransactionId(UUID.randomUUID().toString());

        // Payment Status
        payment.setPaymentStatus("SUCCESS");

        // Payment Date
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get Payment By Booking
    public Payment getPaymentByBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        return paymentRepository.findByBooking(booking);
    }

    // Get Payments By Status
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    // Get Payments By Method
    public List<Payment> getPaymentsByMethod(String method) {
        return paymentRepository.findByPaymentMethod(method);
    }

    // Update Payment Status
    public Payment updatePaymentStatus(Long paymentId,
                                       String status) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        payment.setPaymentStatus(status);

        return paymentRepository.save(payment);
    }

    // Delete Payment
    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}