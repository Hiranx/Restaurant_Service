package com.hiran.restaurantService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "orders_check")
@Data
public class Order {
    @Id
    private String id;
    private String restaurantId;
    private List<OrderItem> item;
    private String status;
}

