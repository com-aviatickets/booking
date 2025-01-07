package com.aviatickets.booking.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "booking_booking")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_booking_seq")
    @SequenceGenerator(
            name = "booking_booking_seq",
            sequenceName = "booking_booking_seq",
            allocationSize = 1
    )
    private Long id;
    private Long flightId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus status = BookingStatus.NEW;
    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
