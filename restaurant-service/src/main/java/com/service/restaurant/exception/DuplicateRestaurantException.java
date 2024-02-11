package com.service.restaurant.exception;

public class DuplicateRestaurantException extends RuntimeException {

    public DuplicateRestaurantException(){
        super();
    }

    public DuplicateRestaurantException(String message){
        super(message);
    }
}
