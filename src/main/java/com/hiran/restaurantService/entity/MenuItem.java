package com.hiran.restaurantService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "menu_items")
@Data
public class MenuItem {
    @Id
    private String id;
    private String restaurantId;
    private String name;
    private double price;
    private boolean isAvailable = true;
}
