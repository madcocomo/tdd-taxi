package com.jiker.keju;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgDataParser {

    public static final Pattern PATTERN = Pattern.compile("^(.+)公里,等待(.+)分钟$");

    public Args parse(String data) {
        Matcher matcher = PATTERN.matcher(data);
        if (matcher.find()) {
            BigDecimal distance = new BigDecimal(matcher.group(1));
            BigDecimal waiting = new BigDecimal(matcher.group(2));
            return new Args(distance, waiting);
        }
        throw new IllegalArgumentException("illegal test data: " + data);
    }
}
