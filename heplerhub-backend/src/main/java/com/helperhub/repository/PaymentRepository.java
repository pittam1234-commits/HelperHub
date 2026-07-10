package com.helperhub.repository;

import com.helperhub.entity.Booking;
import com.helperhub.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Get payment by booking
    Payment findByBooking(Booking booking);

    // Get payments by status
    List<Payment> findByPaymentStatus(String paymentStatus);

    // Get payments by payment method
    List<Payment> findByPaymentMethod(String paymentMethod);
}