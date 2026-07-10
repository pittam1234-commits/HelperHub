package com.helperhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String phone;

    private String email;

    private String city;

    private int experience;

    private double pricePerHour;

    private boolean available;

    private String status;

    @Column(name = "image_url")
    private String imageUrl;

    public Worker() {
        this.status = "AVAILABLE";
        this.available = true;
    }

    public Worker(Long id, String name, String category, String phone,
                  String email, String city, int experience,
                  double pricePerHour, boolean available,
                  String status, String imageUrl) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.experience = experience;
        this.pricePerHour = pricePerHour;
        this.available = available;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

        if(status.equalsIgnoreCase("AVAILABLE")){
            this.available=true;
        }else{
            this.available=false;
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}