package com.hiran.restaurantService.controller;

import com.hiran.restaurantService.entity.OrderItem;
import com.hiran.restaurantService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public ResponseEntity<String> handleOrder(@PathVariable String restaurantId, @RequestBody List<OrderItem> items) {

        return null;
    }
}
