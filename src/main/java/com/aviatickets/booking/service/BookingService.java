package com.aviatickets.booking.service;

import com.aviatickets.booking.controller.request.BookingRequest;
import com.aviatickets.booking.exception.BadRequestException;
import com.aviatickets.booking.model.Booking;
import com.aviatickets.booking.model.BookingStatus;
import com.aviatickets.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingService {

    public static final String BOOKING_NOT_FOUND = "Booking by id %d not found";
    public static final String BOOKING_ALREADY_COMPLETED = "Booking with id %d is already completed";
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking create(BookingRequest request) {
        Booking booking = Booking.builder()
                .flightId(request.flightId())
                .userId(request.userId()).build();

        return save(booking);
    }

    public List<Booking> findAllByUserId(Long userId) {
        return bookingRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    @Transactional
    public Booking cancel(Long bookingId) {
        Booking booking = findById(bookingId);

        if(BookingStatus.COMPLETED_STATUSES.contains(booking.getStatus())) {
            throw new BadRequestException(BOOKING_ALREADY_COMPLETED.formatted(bookingId));
        }

        if(BookingStatus.PAID.equals(booking.getStatus())) {
            throw new BadRequestException("Booking with id %d is paid".formatted(bookingId));
        }

        booking.setStatus(BookingStatus.CANCELED);

        return save(booking);
    }

    private Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public Booking findById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new NoSuchElementException(BOOKING_NOT_FOUND.formatted(bookingId)));
    }

}
