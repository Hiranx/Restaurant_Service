package com.hiran.restaurantService.dto;

import lombok.Data;

@Data
public class RestaurantDTO {
    private String id;
    private String name;
    private boolean isAvailable;
    private String address;
    private String contactNumber;
    private String cuisineType;
    private String openingTime;
    private String closingTime;
}
