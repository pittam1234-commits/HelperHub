package com.helperhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stars;

    @Column(length = 500)
    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    // Default Constructor
    public Rating() {
    }

    // Parameterized Constructor
    public Rating(Long id, int stars, String review, User user, Worker worker) {
        this.id = id;
        this.stars = stars;
        this.review = review;
        this.user = user;
        this.worker = worker;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", stars=" + stars +
                ", review='" + review + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                ", worker=" + (worker != null ? worker.getId() : null) +
                '}';
    }
}