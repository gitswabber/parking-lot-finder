package com.swabber.api.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeUtilsTest {

    @Test
    void isWeekday() {
        final LocalDateTime now = LocalDateTime.now();
        System.out.println("Today is " + now.getDayOfWeek());
        System.out.println("Is weekend today? " + DateTimeUtils.isWeekend(now));
    }

    @Test
    void getHourMinute() {
        System.out.println("Now is "+DateTimeUtils.getHourMinute(LocalDateTime.now()));
        System.out.println("Now is "+DateTimeUtils.getHourMinute(LocalDateTime.of(2020, 1, 12, 9, 10)));
    }
}