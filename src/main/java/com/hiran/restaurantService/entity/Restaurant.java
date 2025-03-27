package com.hiran.restaurantService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "restaurants")
@Data
public class Restaurant {
    @Id
    private String id;
    private String name;
    private boolean isAvailable = true;
}

