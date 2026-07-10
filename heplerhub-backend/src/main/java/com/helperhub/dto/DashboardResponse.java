package com.helperhub.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalWorkers;
    private long totalBookings;
    private long totalPayments;
    private double totalRevenue;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalUsers,
                             long totalWorkers,
                             long totalBookings,
                             long totalPayments,
                             double totalRevenue) {

        this.totalUsers = totalUsers;
        this.totalWorkers = totalWorkers;
        this.totalBookings = totalBookings;
        this.totalPayments = totalPayments;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalWorkers() {
        return totalWorkers;
    }

    public void setTotalWorkers(long totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}