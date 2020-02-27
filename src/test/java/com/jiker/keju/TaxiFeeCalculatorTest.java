package com.jiker.keju;

import org.junit.Test;

import static com.jiker.keju.TaxiFeeCalculator.MINIMAL_FEE;
import static org.junit.Assert.*;

public class TaxiFeeCalculatorTest {
    TaxiFeeCalculator calculator = new TaxiFeeCalculator();

    @Test
    public void should_calculate_fee_and_format_to_string() {
        assertEquals("收费6元", calculator.calculate(1, 0));
    }

    @Test
    public void should_format_fee_to_display_string() {
        assertEquals("收费6元", calculator.format(6));
    }

    @Test
    public void should_return_minimal_fee_when_distance_not_more_than_2() {
        assertEquals(MINIMAL_FEE, calculator.calculateFee(1, 0));
        assertEquals(MINIMAL_FEE, calculator.calculateFee(2, 0));
    }

    @Test
    public void should_return_zero_when_no_distance() {
        assertEquals(0, calculator.calculateFee(0, 0));
    }
}
