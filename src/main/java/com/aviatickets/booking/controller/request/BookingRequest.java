package com.aviatickets.booking.controller.request;

public record BookingRequest(
        Long userId,
        Long flightId
) {
}