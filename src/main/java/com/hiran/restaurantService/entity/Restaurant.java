package com.hiran.restaurantService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "restaurants")
@Data
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;          // Simple address field
    private String contactNumber;    // Phone number for contact
    private String cuisineType;      // e.g., "Italian", "Chinese"
    private boolean isAvailable = true;
    private String openingTime;   // Opening time (e.g., 09:00)
    private String closingTime;
    private boolean approved = false; // New field
    private String registrationDate = String.valueOf(LocalDateTime.now()); // New field
}

