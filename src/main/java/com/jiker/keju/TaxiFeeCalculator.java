package com.jiker.keju;

import java.math.BigDecimal;

public class TaxiFeeCalculator {

    public static final BigDecimal MINIMAL_FEE = BigDecimal.valueOf(6);
    public static final BigDecimal SHORT_DISTANCE = BigDecimal.valueOf(2);
    public static final BigDecimal LONG_DISTANCE = BigDecimal.valueOf(8);
    public static final BigDecimal SHORT_PRE_KM = BigDecimal.valueOf(0.8);
    public static final BigDecimal LONG_RATE = BigDecimal.valueOf(0.5);

    public String calculate(BigDecimal distance, BigDecimal waiting) {
        return format(calculateFee(distance, waiting));
    }

    String format(BigDecimal fee) {
        return String.format("收费%d元", fee.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
    }

    public BigDecimal calculateFee(BigDecimal distance, BigDecimal waiting) {
        BigDecimal fee = BigDecimal.ZERO;
        if (distance.compareTo(BigDecimal.ZERO) > 0) {
            fee = MINIMAL_FEE;
        }
        if (distance.compareTo(SHORT_DISTANCE) > 0) {
            fee = fee.add(distance.subtract(SHORT_DISTANCE).multiply(SHORT_PRE_KM));
        }
        if (distance.compareTo(LONG_DISTANCE) > 0) {
            fee = fee.add(distance.subtract(LONG_DISTANCE).multiply(SHORT_PRE_KM).multiply(LONG_RATE));
        }
        return fee;
    }
}
