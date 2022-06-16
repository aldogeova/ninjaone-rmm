package com.ninjaone.utils;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.trim;
import static org.apache.commons.lang3.StringUtils.upperCase;

public class NinjaStringUtils {

    public static String defaultFormat(String unformat) {
        if(unformat != null) {
            return trim(upperCase(unformat));
        }
        return null;
    }

    public static String stripAccents(String unformat) {
        if(unformat != null) {
            return StringUtils.stripAccents(unformat);
        }
        return null;
    }

    public static boolean isNotBlank(String value) {
        return StringUtils.isNotBlank(value);
    }

    public static boolean isBlank(String value) {
        return StringUtils.isBlank(value);
    }

}
