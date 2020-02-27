package com.jiker.keju;

import org.junit.Test;

import java.math.BigDecimal;

import static com.jiker.keju.TaxiFeeCalculator.MINIMAL_FEE;
import static org.junit.Assert.assertEquals;

public class TaxiFeeCalculatorTest {
    TaxiFeeCalculator calculator = new TaxiFeeCalculator();

    @Test
    public void should_calculate_fee_and_format_to_string() {
        assertEquals("收费6元", calculator.calculate(decimal(1), decimal(0)));
    }

    private BigDecimal decimal(int i) {
        return BigDecimal.valueOf(i);
    }

    private BigDecimal decimal(double v) {
        return BigDecimal.valueOf(v).setScale(4, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

    @Test
    public void should_format_fee_to_display_string() {
        assertEquals("收费6元", calculator.format(decimal(6)));
    }

    @Test
    public void should_format_decimal_fee_to_floor_int() {
        assertEquals("收费6元", calculator.format(decimal(6.3)));
        assertEquals("收费7元", calculator.format(decimal(6.7)));
        assertEquals("收费7元", calculator.format(new BigDecimal("7.20000")));
    }

    @Test
    public void should_return_minimal_fee_when_distance_not_more_than_2() {
        assertEquals(MINIMAL_FEE, calculator.calculateFee(decimal(1), decimal(0)));
        assertEquals(MINIMAL_FEE, calculator.calculateFee(decimal(2), decimal(0)));
    }

    @Test
    public void should_return_zero_when_no_distance() {
        assertEquals(decimal(0), calculator.calculateFee(decimal(0), decimal(0)));
    }

    @Test
    public void should_add_short_distance_fee_when_distance_between_2_and_8() {
        assertEquals(decimal(6 + 0.8 * 1), calculator.calculateFee(decimal(3), decimal(0)));
    }

    @Test
    public void should_multiple_long_distance_fee_when_distance_more_than_8() {
        assertEquals(decimal(6 + 0.8*6 + 0.8*1.5*2), calculator.calculateFee(decimal(10), decimal(0)).stripTrailingZeros());
    }

    @Test
    public void should_add_waiting_fee_if_has_waiting_time() {
        assertEquals(decimal(6 + 3*0.25), calculator.calculateFee(decimal(2), decimal(3)).stripTrailingZeros());
    }

    @Test
    public void should_treat_faction_distance_as_one_KM() {
        assertEquals("收费13元", calculator.calculate(decimal(9.2), decimal(0)));
    }

    @Test
    public void should_treat_faction_waiting_as_one_minute() {
        assertEquals("收费7元", calculator.calculate(decimal(2), decimal(1.3)));
    }
}
