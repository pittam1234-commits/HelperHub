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

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment makePayment(
            Long bookingId,
            double amount,
            String paymentMethod) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        Payment existingPayment =
                paymentRepository.findByBookingId(bookingId);

        if (existingPayment != null) {
            throw new RuntimeException(
                    "Payment already completed for Booking #" + bookingId
            );
        }

        if (!"APPROVED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException(
                    "Payment allowed only for APPROVED booking"
            );
        }

        Payment payment = new Payment();

        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        booking.setStatus("PAID");
        bookingRepository.save(booking);

        return savedPayment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentByBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking Not Found"));

        return paymentRepository.findByBooking(booking);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    public List<Payment> getPaymentsByMethod(String method) {
        return paymentRepository.findByPaymentMethod(method);
    }

    public Payment updatePaymentStatus(
            Long paymentId,
            String status) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment Not Found"));

        payment.setPaymentStatus(status);

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
