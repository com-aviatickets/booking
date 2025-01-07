package com.aviatickets.booking.util.http;

import java.util.Collection;

public record ListResult<T>(
        Collection<? extends T> data
) {
}
