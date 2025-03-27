package com.hiran.restaurantService.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private String name;
    private String description;
    private double price;
    private String category;
}