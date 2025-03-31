package com.hiran.restaurantService.service;

import com.hiran.restaurantService.entity.Restaurant;
import com.hiran.restaurantService.repository.RestaurantRepository;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant toggleAvailability(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new RuntimeException("Restaurant not found"));
        restaurant.setAvailable(!restaurant.isAvailable());
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getPendingRestaurants() {
        return restaurantRepository.findByApprovedFalse();
    }

    public Restaurant updateApprovalStatus(String id, boolean approved) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setApproved(approved);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        restaurant.setApproved(false);
        restaurant.setAvailable(false);
        restaurant.setRegistrationDate(String.valueOf(LocalDateTime.now()));
        return restaurantRepository.save(restaurant);
    }

    // Read All
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Read by ID
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    // Update
    public Restaurant updateRestaurant(String id, Restaurant updatedRestaurant) {
        Restaurant existing = getRestaurantById(id);
        existing.setName(updatedRestaurant.getName());
        existing.setAddress(updatedRestaurant.getAddress());
        // Update other fields as needed
        return restaurantRepository.save(existing);
    }

    // Delete
    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }
}
