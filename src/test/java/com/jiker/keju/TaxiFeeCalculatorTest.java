package com.jiker.keju;

import org.junit.Test;

import java.math.BigDecimal;

import static com.jiker.keju.TaxiFeeCalculator.MINIMAL_FEE;
import static org.junit.Assert.assertEquals;

public class TaxiFeeCalculatorTest {
    TaxiFeeCalculator calculator = new TaxiFeeCalculator();

    @Test
    public void should_calculate_fee_and_format_to_string() {
        assertEquals("收费6元", calculator.calculate(1, 0));
    }

    private BigDecimal decimal(int i) {
        return BigDecimal.valueOf(i);
    }

    private BigDecimal decimal(double v) {
        return BigDecimal.valueOf(v);
    }

    @Test
    public void should_format_fee_to_display_string() {
        assertEquals("收费6元", calculator.format(decimal(6)));
    }

    @Test
    public void should_format_decimal_fee_to_floor_int() {
        assertEquals("收费6元", calculator.format(decimal(6.3f)));
        assertEquals("收费7元", calculator.format(decimal(6.7f)));
    }

    @Test
    public void should_return_minimal_fee_when_distance_not_more_than_2() {
        assertEquals(MINIMAL_FEE, calculator.calculateFee(1, 0));
        assertEquals(MINIMAL_FEE, calculator.calculateFee(2, 0));
    }

    @Test
    public void should_return_zero_when_no_distance() {
        assertEquals(decimal(0), calculator.calculateFee(0, 0));
    }

    @Test
    public void should_add_short_distance_fee_when_distance_between_2_and_8() {
        assertEquals(decimal(6 + 0.8 * 1), calculator.calculateFee(3, 0));
    }
}
