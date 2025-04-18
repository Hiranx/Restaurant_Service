package com.hiran.restaurantService.controller;

import com.hiran.restaurantService.entity.Restaurant;
import com.hiran.restaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PutMapping("/{id}/availability")
    public Restaurant toggleAvailability(@PathVariable String id) {

        return restaurantService.toggleAvailability(id);
    }

    @PostMapping("/create")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/view/{id}")
    public Restaurant getById(@PathVariable String id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/update/{id}")
    public Restaurant update(@PathVariable String id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
    }
}
