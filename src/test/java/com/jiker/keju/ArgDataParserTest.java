package com.jiker.keju;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestDataParserArg {
    @Test
    public void should_get_int_distance_and_waiting_from_string() {
        TestData testData = ArgDataParser.parse("1公里,等待0分钟");
        assertEquals(BigDecimal.valueOf(1), testData.getDistance());
        assertEquals(BigDecimal.valueOf(0), testData.getWaiting());
    }

    @Test
    public void should_get_float_distance_and_waiting_from_string() {
        TestData testData = ArgDataParser.parse("1.5公里,等待0.5分钟");
        assertEquals(BigDecimal.valueOf(1.5), testData.getDistance());
        assertEquals(BigDecimal.valueOf(0.5), testData.getWaiting());
    }

    @Test
    public void should_throw_exception_if_not_in_right_format() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArgDataParser.parse("3公,等5分");
        });
    }
}