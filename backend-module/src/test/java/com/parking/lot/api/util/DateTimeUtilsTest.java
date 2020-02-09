package com.parking.lot.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeUtilsTest {

    @Test
    void isWeekday() {
        final LocalDateTime friday = LocalDateTime.of(2020, 2, 7, 11, 10);
        final LocalDateTime saturday = LocalDateTime.of(2020, 2, 8, 11, 10);
        Assertions.assertFalse(DateTimeUtils.isWeekend(friday));
        Assertions.assertTrue(DateTimeUtils.isWeekend(saturday));
    }

    @Test
    void getHourMinute() {
        final LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 8, 11, 10);
        Assertions.assertEquals("1110", DateTimeUtils.getHourMinute(localDateTime));
        final LocalDateTime localDateTime2 = LocalDateTime.of(2020, 2, 8, 9, 8);
        Assertions.assertEquals("0908", DateTimeUtils.getHourMinute(localDateTime2));
    }
}