package com.service.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<String> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(DuplicateRestaurantException.class)
    public ResponseEntity<String> handleDuplicateRestaurantException(DuplicateRestaurantException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<String> handleRestaurantAlreadyExistsException(RestaurantAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Define more exception handlers for other exceptions...

    // Generic exception handler for any other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
