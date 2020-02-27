package com.jiker.keju;

public class TaxiFeeCalculator {

    public static final int MINIMAL_FEE = 6;

    public String calculate(float distance, float waiting) {
        return format(calculateFee(distance, waiting));
    }

    String format(int fee) {
        return String.format("收费%d元", fee);
    }

    public int calculateFee(float distance, float waiting) {
        if (distance == 0) {
            return 0;
        }
        return MINIMAL_FEE;
    }
}
