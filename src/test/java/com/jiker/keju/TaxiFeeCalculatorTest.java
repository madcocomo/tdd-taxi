package com.jiker.keju;

import org.junit.Test;

import static com.jiker.keju.TaxiFeeCalculator.MINIMAL_FEE;
import static org.junit.Assert.*;

public class TaxiFeeCalculatorTest {
    private static final float DELTA = 0.0001f;
    TaxiFeeCalculator calculator = new TaxiFeeCalculator();

    @Test
    public void should_calculate_fee_and_format_to_string() {
        assertEquals("收费6元", calculator.calculate(1, 0));
    }

    @Test
    public void should_format_fee_to_display_string() {
        assertEquals("收费6元", calculator.format(6f));
    }

    @Test
    public void should_format_decimal_fee_to_floor_int() {
        assertEquals("收费6元", calculator.format(6.3f));
        assertEquals("收费7元", calculator.format(6.7f));
    }

    @Test
    public void should_return_minimal_fee_when_distance_not_more_than_2() {
        assertEquals(MINIMAL_FEE, calculator.calculateFee(1, 0), DELTA);
        assertEquals(MINIMAL_FEE, calculator.calculateFee(2, 0), DELTA);
    }

    @Test
    public void should_return_zero_when_no_distance() {
        assertEquals(0, calculator.calculateFee(0, 0), DELTA);
    }

    @Test
    public void should_add_short_distance_fee_when_distance_between_2_and_8() {
        assertEquals(MINIMAL_FEE + 0.8 * 1, calculator.calculateFee(3, 0), DELTA);
    }
}
