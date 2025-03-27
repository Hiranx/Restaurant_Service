package com.hiran.restaurantService.repository;

import com.hiran.restaurantService.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByIsAvailable(boolean isAvailable);
}

