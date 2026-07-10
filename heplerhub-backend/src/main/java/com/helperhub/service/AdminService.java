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
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public DashboardResponse getDashboard() {

        DashboardResponse dashboard = new DashboardResponse();

        dashboard.setTotalUsers(userRepository.count());
        dashboard.setTotalWorkers(workerRepository.count());
        dashboard.setTotalBookings(bookingRepository.count());
        dashboard.setTotalPayments(paymentRepository.count());

        List<Payment> payments = paymentRepository.findAll();

        double revenue = 0;

        for (Payment payment : payments) {
            revenue += payment.getAmount();
        }

        dashboard.setTotalRevenue(revenue);

        return dashboard;
    }
}