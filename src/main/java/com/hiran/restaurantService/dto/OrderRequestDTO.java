package com.hiran.restaurantService.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<OrderItemDTO> items;
}

@Data
class OrderItemDTO {
    private String itemId;
    private int quantity;
}
