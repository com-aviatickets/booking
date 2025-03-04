package com.aviatickets.booking.exception;

public class UserNotAuthenticated extends RuntimeException {

    public UserNotAuthenticated(String message) {
        super(message);
    }
}
