package com.jiker.keju;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestDataParserTest {

    public static final double DELTA = 0.0001;

    @Test
    public void should_get_int_distance_and_waiting_from_string() {
        TestData testData = TestDataParser.parse("1公里,等待0分钟");
        assertEquals(1f, testData.getDistance(), DELTA);
        assertEquals(0f, testData.getWaiting(), DELTA);
    }

    @Test
    public void should_get_float_distance_and_waiting_from_string() {
        TestData testData = TestDataParser.parse("1.5公里,等待0.5分钟");
        assertEquals(1.5f, testData.getDistance(), DELTA);
        assertEquals(0.5f, testData.getWaiting(), DELTA);
    }

    @Test
    public void should_throw_exception_if_not_in_right_format() {
        assertThrows(IllegalArgumentException.class, () -> {
            TestDataParser.parse("3公,等5分");
        });
    }
}