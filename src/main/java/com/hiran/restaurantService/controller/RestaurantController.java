package com.hiran.restaurantService.controller;

import com.hiran.restaurantService.entity.Restaurant;
import com.hiran.restaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PutMapping("/{id}/availability")
    public Restaurant toggleAvailability(@PathVariable String id) {
        return restaurantService.toggleAvailability(id);
    }
}
