package com.hiran.restaurantService.entity;

import lombok.Data;

@Data
public class OrderItem {
    private String itemId;
    private int quantity;
}
