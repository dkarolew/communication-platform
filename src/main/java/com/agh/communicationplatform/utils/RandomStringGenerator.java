package com.agh.communicationplatform.utils;

import java.security.SecureRandom;
import java.util.Locale;

public class RandomStringGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = UPPER + LOWER + DIGITS;
    private static final SecureRandom random = new SecureRandom();

    public static String generate(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            builder.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        return builder.toString();
    }
}
