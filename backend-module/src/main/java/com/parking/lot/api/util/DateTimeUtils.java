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
        return (hour < 10 ? "0" : "") + hour + (minute < 10 ? "0" : "") + minute;
    }
}
