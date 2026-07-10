package com.helperhub.service;

import com.helperhub.dto.DashboardResponse;
import com.helperhub.entity.Payment;
import com.helperhub.repository.BookingRepository;
import com.helperhub.repository.PaymentRepository;
import com.helperhub.repository.UserRepository;
import com.helperhub.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public DashboardResponse getDashboard() {

        long totalUsers = userRepository.count();

        long totalWorkers = workerRepository.count();

        long totalBookings = bookingRepository.count();

        long totalPayments = paymentRepository.count();

        List<Payment> payments = paymentRepository.findAll();

        double totalRevenue = 0;

        for (Payment payment : payments) {

            if ("SUCCESS".equalsIgnoreCase(payment.getPaymentStatus())) {

                totalRevenue += payment.getAmount();

            }
        }

        DashboardResponse dashboard = new DashboardResponse();

        dashboard.setTotalUsers(totalUsers);
        dashboard.setTotalWorkers(totalWorkers);
        dashboard.setTotalBookings(totalBookings);
        dashboard.setTotalPayments(totalPayments);
        dashboard.setTotalRevenue(totalRevenue);

        return dashboard;
    }
}