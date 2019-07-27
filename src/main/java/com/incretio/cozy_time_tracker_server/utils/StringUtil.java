package com.incretio.cozy_time_tracker_server.utils;

public class StringUtil {
    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }
}
