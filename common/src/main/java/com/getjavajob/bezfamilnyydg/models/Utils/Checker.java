package com.getjavajob.bezfamilnyydg.models.Utils;

public class Checker {
    private static String CHECK_NULL_MESSAGE = " = null";

    public static <T> T checkNull(T value, String message) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException(message + CHECK_NULL_MESSAGE);
        } else {
            return value;
        }
    }

    public static <T> T checkNullWithExactMessage(T value, String message) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }
}
