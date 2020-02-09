package com.parking.lot.api.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public final class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static boolean isWeekend(LocalDateTime localDateTime) {
        final DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        return dayOfWeek.getValue() > DayOfWeek.FRIDAY.getValue();
    }

    public static String getHourMinute(LocalDateTime localDateTime) {
        final int hour = localDateTime.getHour();
        final int minute = localDateTime.getMinute();
        // To add prefix "0" when time value is 1 digit.
        return (hour < 10 ? "0" : "") + hour + (minute < 10 ? "0" : "") + minute;
    }
}
