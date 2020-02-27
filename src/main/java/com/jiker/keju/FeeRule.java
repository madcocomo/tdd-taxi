package com.jiker.keju;

import java.math.BigDecimal;

public interface FeeRule {
    BigDecimal MINIMAL_FEE = BigDecimal.valueOf(6);
    BigDecimal SHORT_DISTANCE = BigDecimal.valueOf(2);
    BigDecimal LONG_DISTANCE = BigDecimal.valueOf(8);
    BigDecimal SHORT_PRE_KM = BigDecimal.valueOf(0.8);
    BigDecimal LONG_RATE = BigDecimal.valueOf(0.5);
    BigDecimal WAITING_RATE = BigDecimal.valueOf(0.25);

    FeeRule MINIMAL_DISTANCE_RULE = (distance, fee) -> {
        if (isBiggerThan(distance, BigDecimal.ZERO)) {
            fee = MINIMAL_FEE;
        }
        return fee;
    };
    FeeRule SHORT_DISTANCE_RULE = new RangeFeeRule(SHORT_DISTANCE, SHORT_PRE_KM);
    FeeRule LONG_DISTANCE_RULE = new RangeFeeRule(LONG_DISTANCE, SHORT_PRE_KM.multiply(LONG_RATE));
    FeeRule WAITING_TIME_RULE = new RangeFeeRule(BigDecimal.ZERO, WAITING_RATE);

    BigDecimal calculate(BigDecimal fact, BigDecimal fee);

    static boolean isBiggerThan(BigDecimal waiting, BigDecimal zero) {
        return waiting.compareTo(zero) > 0;
    }

    class RangeFeeRule implements FeeRule {
        private final BigDecimal limit;

        private final BigDecimal rate;

        public RangeFeeRule(BigDecimal limit, BigDecimal rate) {
            this.limit = limit;
            this.rate = rate;
        }

        @Override
        public BigDecimal calculate(BigDecimal fact, BigDecimal fee) {
            if (isBiggerThan(fact, limit)) {
                return fee.add(fact.subtract(limit).multiply(rate));
            }
            return fee;
        }

    }
}
