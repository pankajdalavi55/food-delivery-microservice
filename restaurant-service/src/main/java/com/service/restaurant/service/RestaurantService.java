package com.service.restaurant.service;

import com.service.restaurant.entity.Restaurant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface RestaurantService {

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Long id);

    void deleteRestaurant(Long id);

    Restaurant updateRestaurant(Long id, Restaurant restaurant);

    Restaurant createRestaurant(Restaurant restaurant);
}
