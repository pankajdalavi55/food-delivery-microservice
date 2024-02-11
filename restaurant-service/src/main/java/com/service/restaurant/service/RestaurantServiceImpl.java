package com.service.restaurant.service;

import com.service.restaurant.entity.Restaurant;
import com.service.restaurant.exception.DuplicateRestaurantException;
import com.service.restaurant.exception.RestaurantNotFoundException;
import com.service.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Method to retrieve all restaurants
    public List<Restaurant> getAllRestaurants() {
        log.info("Fetching all restaurants");
        return restaurantRepository.findAll();
    }

    // Method to retrieve a restaurant by ID
    public Restaurant getRestaurantById(Long id) {
        log.info("Fetching restaurant with ID: {}", id);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            return restaurantOptional.get();
        } else {
            log.error("Restaurant not found with ID: {}", id);
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + id);
        }
    }

    // Method to create a new restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        log.info("Creating new restaurant: {}", restaurant);
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByNameAndAddress(restaurant.getName(), restaurant.getAddress());
        if (existingRestaurant.isEmpty()) {
            if (restaurant.getFoodItems() != null) restaurant.getFoodItems().forEach(e -> e.setRestaurant(restaurant));
            return restaurantRepository.save(restaurant);
        } else {
            log.error("DuplicateRestaurantException for restaurant name :{} and Address :{}", restaurant.getName(), restaurant.getAddress());
            throw new DuplicateRestaurantException("Yay!! Your Restaurant Is already existed..!");
        }

    }

    // Method to update an existing restaurant
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        log.info("Updating restaurant with ID: {}", id);
        Optional<Restaurant> existingRestaurantOptional = restaurantRepository.findById(id);
        if (existingRestaurantOptional.isPresent()) {
            Restaurant existingRestaurant = getRestaurant(restaurant, existingRestaurantOptional.get());
            existingRestaurant.setActive(restaurant.isActive());
            return restaurantRepository.save(existingRestaurant);
        } else {
            log.error("Restaurant not found with ID: {}", id);
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + id);
        }
    }

    private static Restaurant getRestaurant(Restaurant restaurant, Restaurant existingRestaurant) {
        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setDescription(restaurant.getDescription());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        existingRestaurant.setStartTime(restaurant.getStartTime());
        existingRestaurant.setCloseTime(restaurant.getCloseTime());
        return existingRestaurant;
    }

    // Method to delete a restaurant by ID
    public void deleteRestaurant(Long id) {
        log.info("Deleting restaurant with ID: {}", id);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            restaurantRepository.deleteById(id);
        } else {
            log.error("Restaurant not found with ID: {}", id);
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + id);
        }
    }
}
