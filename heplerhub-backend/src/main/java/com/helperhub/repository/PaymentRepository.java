package com.helperhub.repository;

import com.helperhub.entity.Booking;
import com.helperhub.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByBooking(Booking booking);

    Payment findByBookingId(Long bookingId);

    List<Payment> findByPaymentStatus(String paymentStatus);

    List<Payment> findByPaymentMethod(String paymentMethod);
}
