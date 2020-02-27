package com.jiker.keju;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDataParser {

    public static final Pattern PATTERN = Pattern.compile("^(.+)公里,等待(.+)分钟$");

    public static TestData parse(String data) {
        Matcher matcher = PATTERN.matcher(data);
        if (matcher.find()) {
            float distance = Float.parseFloat(matcher.group(1));
            float waiting = Float.parseFloat(matcher.group(2));;
            return new TestData(distance, waiting);
        }
        throw new IllegalArgumentException("illegal test data: " + data);
    }
}
