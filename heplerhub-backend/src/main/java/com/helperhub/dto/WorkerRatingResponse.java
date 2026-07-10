package com.helperhub.dto;

public class WorkerRatingResponse {

    private Long workerId;
    private String workerName;
    private double averageRating;
    private int totalReviews;

    public WorkerRatingResponse() {
    }

    public WorkerRatingResponse(Long workerId,
                                String workerName,
                                double averageRating,
                                int totalReviews) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }
}