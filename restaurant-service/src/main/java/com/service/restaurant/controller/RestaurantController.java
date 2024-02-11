package com.service.restaurant.controller;

import com.service.restaurant.entity.Restaurant;
import com.service.restaurant.exception.DuplicateRestaurantException;
import com.service.restaurant.exception.RestaurantNotFoundException;
import com.service.restaurant.response.APIResponse;
import com.service.restaurant.response.ErrorResponse;
import com.service.restaurant.response.SuccessResponse;
import com.service.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/view-restaurants")
    public ResponseEntity<APIResponse> getAllRestaurants() {
        try {
            List<Restaurant> restaurantList = restaurantService.getAllRestaurants();
            return new ResponseEntity<APIResponse>(new SuccessResponse<List<Restaurant>>(true, "Retrieved restaurants successfully", restaurantList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view-restaurants/{id}")
    public ResponseEntity<APIResponse> getRestaurantById(@PathVariable Long id) throws RestaurantNotFoundException {
        try {
            Restaurant restaurant = restaurantService.getRestaurantById(id);
            return new ResponseEntity<APIResponse>(new SuccessResponse<Restaurant>(true, "Retrieved restaurant successfully", restaurant), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-restaurant")
    public ResponseEntity<APIResponse> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.createRestaurant(restaurant);
            return new ResponseEntity<APIResponse>(new SuccessResponse<Restaurant>(true, "Restaurant created successfully", restaurant), HttpStatus.CREATED);
        } catch (DuplicateRestaurantException e){
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modify-restaurant/{id}")
    public ResponseEntity<APIResponse> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.updateRestaurant(id, restaurant);
            return new ResponseEntity<>(new SuccessResponse<>(true, "Restaurant updated successfully", restaurant), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-restaurant/{id}")
    public ResponseEntity<APIResponse> deleteRestaurant(@PathVariable Long id) {
        try {
            restaurantService.deleteRestaurant(id);
            return new ResponseEntity<APIResponse>(new SuccessResponse<>(true, "Restaurant deleted successfully", ""), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
