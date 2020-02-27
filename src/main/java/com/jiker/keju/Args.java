package com.jiker.keju;

import java.math.BigDecimal;

public class TestData {
    private BigDecimal distance;
    private BigDecimal waiting;

    public TestData(BigDecimal distance, BigDecimal waiting) {
        this.distance = distance;
        this.waiting = waiting;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getWaiting() {
        return waiting;
    }
}
