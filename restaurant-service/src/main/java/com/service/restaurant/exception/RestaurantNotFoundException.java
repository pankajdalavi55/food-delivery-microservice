package com.service.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super();
    }
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
