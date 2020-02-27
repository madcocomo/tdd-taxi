package com.jiker.keju;

import java.math.BigDecimal;

public class TaxiFeeCalculator {

    public static final BigDecimal MINIMAL_FEE = BigDecimal.valueOf(6);

    public String calculate(float distance, float waiting) {
        return format(calculateFee(distance, waiting));
    }

    String format(BigDecimal fee) {
        return String.format("收费%d元", fee.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
    }

    public BigDecimal calculateFee(float distance, float waiting) {
        BigDecimal fee = BigDecimal.ZERO;
        if (distance > 0) {
            fee = MINIMAL_FEE;
        }
        if (distance > 2) {
            fee = fee.add(BigDecimal.valueOf((distance - 2) * 0.8d));
        }
        return fee;
    }
}
