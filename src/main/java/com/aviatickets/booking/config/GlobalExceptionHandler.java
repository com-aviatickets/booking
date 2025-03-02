package com.aviatickets.booking.config;

import com.aviatickets.booking.controller.response.ErrorDto;
import com.aviatickets.booking.exception.BadRequestException;
import com.aviatickets.booking.exception.UserNotAuthenticated;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String EXCEPTION_MESSAGE = "Uncaught exception";

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<?> handleException(Exception e) {
        log.error(EXCEPTION_MESSAGE, e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        log.error(EXCEPTION_MESSAGE, e);
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<?> handleValidationException(ValidationException e) {
        log.error(EXCEPTION_MESSAGE, e);
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        log.error(EXCEPTION_MESSAGE, e);
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAuthenticated.class)
    protected ResponseEntity<?> handleUserNotAuthenticated(UserNotAuthenticated e) {
        log.error(EXCEPTION_MESSAGE, e);
        return buildErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<?> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(
                new ErrorDto(message, status.getReasonPhrase(), status.value()),
                status
        );
    }

}
