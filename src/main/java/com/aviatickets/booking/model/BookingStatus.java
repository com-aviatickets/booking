package com.aviatickets.booking.model;

import java.util.EnumSet;
import java.util.Set;

public enum BookingStatus {

    NEW, PAID,CANCELED, COMPLETED, FAILED, EXPIRED;

    public static final Set<BookingStatus> COMPLETED_STATUSES = EnumSet.of(COMPLETED, FAILED, EXPIRED);

}
