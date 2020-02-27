package com.jiker.keju;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.jiker.keju.FeeRule.LONG_DISTANCE_RULE;
import static com.jiker.keju.FeeRule.MINIMAL_DISTANCE_RULE;
import static com.jiker.keju.FeeRule.SHORT_DISTANCE_RULE;

public class TaxiFeeCalculator {


    public String calculate(BigDecimal distance, BigDecimal waiting) {
        return format(calculateFee(ceil(distance), ceil(waiting)));
    }

    private BigDecimal ceil(BigDecimal distance) {
        return distance.setScale(0, RoundingMode.UP);
    }

    String format(BigDecimal fee) {
        return String.format("收费%d元", fee.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
    }

    public BigDecimal calculateFee(BigDecimal distance, BigDecimal waiting) {
        BigDecimal fee = MINIMAL_DISTANCE_RULE.calculate(distance, BigDecimal.ZERO);
        fee = SHORT_DISTANCE_RULE.calculate(distance, fee);
        fee = LONG_DISTANCE_RULE.calculate(distance, fee);
        fee = FeeRule.WAITING_TIME_RULE.calculate(waiting, fee);
        return fee;
    }
}
