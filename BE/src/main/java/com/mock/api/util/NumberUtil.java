package com.mock.api.util;

public class NumberUtil {

    public static Long parseLong(Object object) {
        if (object == null) return null;
        try {
           return Long.valueOf(String.valueOf(object));
        } catch (IllegalArgumentException exception){
            return null;
        }
    }
}
