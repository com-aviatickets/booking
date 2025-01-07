package com.aviatickets.booking.controller.response;

public record ErrorDto(
        String message,
        String status,
        int code
) {
}
