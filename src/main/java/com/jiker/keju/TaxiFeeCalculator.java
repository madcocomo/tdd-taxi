package com.jiker.keju;

public class TaxiFeeCalculator {

    public static final int MINIMAL_FEE = 6;

    public String calculate(float distance, float waiting) {
        return format(calculateFee(distance, waiting));
    }

    String format(Float fee) {
        return String.format("收费%d元", Math.round(fee));
    }

    public float calculateFee(float distance, float waiting) {
        if (distance == 0) {
            return 0;
        }
        if (distance > 2) {
            return MINIMAL_FEE + (distance - 2) * 0.8f;
        }
        return MINIMAL_FEE;
    }
}
