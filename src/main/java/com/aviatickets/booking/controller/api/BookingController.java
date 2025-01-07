package com.aviatickets.booking.controller.api;

import com.aviatickets.booking.controller.request.BookingRequest;
import com.aviatickets.booking.model.Booking;
import com.aviatickets.booking.service.BookingService;
import com.aviatickets.booking.util.http.HttpUtils;
import com.aviatickets.booking.util.http.ListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.aviatickets.booking.controller.ControllerConstants.API;

@RestController
@RequestMapping(API + "/book")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.create(request);
    }

    @GetMapping("/{id}")
    public Booking findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @GetMapping("/byUser/{userId}")
    public ListResult<Booking> findAllByUserId(@PathVariable Long userId) {
        return HttpUtils.listOk(bookingService.findAllByUserId(userId));
    }

    @PatchMapping("/{bookingId}/cancel")
    public Booking cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancel(bookingId);
    }

}
