package com.parking.lot.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

class DateTimeUtilsTest {

    private static Stream<Arguments> provideForIsWeekday() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2020, 2, 7, 11, 10), false),
                Arguments.of(LocalDateTime.of(2020, 2, 8, 11, 10), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForIsWeekday")
    void isWeekday(LocalDateTime localDateTime, boolean expected) {
        Assertions.assertEquals(expected, DateTimeUtils.isWeekend(localDateTime));
    }

    private static Stream<Arguments> provideForGetHourMinute() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2020, 2, 8, 11, 10), "1110"),
                Arguments.of(LocalDateTime.of(2020, 2, 8, 9, 8), "0908")
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetHourMinute")
    void getHourMinute(LocalDateTime localDateTime, String expected) {
        Assertions.assertEquals(expected, DateTimeUtils.getHourMinute(localDateTime));
    }
}