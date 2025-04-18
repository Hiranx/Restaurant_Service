package com.hiran.restaurantService.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;          // Simple address field
    private String contactNumber;    // Phone number for contact
    private String cuisineType;      // e.g., "Italian", "Chinese"
    private boolean available = false;
    private String openingTime;   // Opening time (e.g., 09:00)
    private String closingTime;
    private String email;
    private String restaurantPassword;
    private boolean approved = false; // New field
    private String registrationDate = String.valueOf(LocalDateTime.now()); // New field


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

