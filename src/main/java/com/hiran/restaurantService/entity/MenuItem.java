package com.hiran.restaurantService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu_items")
@Data
public class MenuItem {
    @Id
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private double price;
    private String category; // e.g., "Appetizer", "Main Course"
    private boolean isAvailable = true;
}
