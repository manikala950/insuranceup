package com.insurance.www.util;

import java.util.HashMap;
import java.util.Map;

public class LocationCodeUtil {

    private static final Map<String, String> STATE_CODES = new HashMap<>();

    static {
        STATE_CODES.put("ANDHRA PRADESH", "AP");
        STATE_CODES.put("TELANGANA", "TS");
        STATE_CODES.put("TAMIL NADU", "TN");
        STATE_CODES.put("KARNATAKA", "KA");
        STATE_CODES.put("MAHARASHTRA", "MH");
        STATE_CODES.put("KERALA", "KL");
        STATE_CODES.put("GUJARAT", "GJ");
        STATE_CODES.put("RAJASTHAN", "RJ");
        STATE_CODES.put("UTTAR PRADESH", "UP");
        STATE_CODES.put("MADHYA PRADESH", "MP");
        STATE_CODES.put("WEST BENGAL", "WB");
        STATE_CODES.put("ODISHA", "OD");
        STATE_CODES.put("PUNJAB", "PB");
        STATE_CODES.put("HARYANA", "HR");
        STATE_CODES.put("BIHAR", "BR");
        STATE_CODES.put("JHARKHAND", "JH");
        STATE_CODES.put("ASSAM", "AS");
        STATE_CODES.put("CHHATTISGARH", "CG");
        STATE_CODES.put("DELHI", "DL");
        STATE_CODES.put("GOA", "GA");
        STATE_CODES.put("UTTARAKHAND", "UK");
        STATE_CODES.put("HIMACHAL PRADESH", "HP");
        STATE_CODES.put("JAMMU AND KASHMIR", "JK");
        STATE_CODES.put("LADAKH", "LA");
    }

    /**
     * Generates location-based agent code
     * Example: AP-PAL-ELD-0001
     */
    public static String generateCode(
            String state,
            String district,
            String mandal,
            String number
    ) {
        String stateCode = resolveStateCode(state);
        String districtCode = shortCode(district);
        String mandalCode = shortCode(mandal);

        return stateCode + "-" +
               districtCode + "-" +
               mandalCode + "-" +
               number;
    }

    /**
     * Converts full state name to short state code
     */
    public static String resolveStateCode(String state) {
        if (state == null || state.trim().isEmpty()) {
            return "XX";
        }

        return STATE_CODES.getOrDefault(
                state.trim().toUpperCase(),
                "XX"
        );
    }

    /**
     * Converts any name to 3-letter uppercase short code
     * Palnadu -> PAL
     * Piduguralla -> PID
     */
    public static String shortCode(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "XXX";
        }

        // Remove non-alphabet characters
        String clean = value.replaceAll("[^A-Za-z]", "")
                            .toUpperCase();

        if (clean.length() >= 3) {
            return clean.substring(0, 3);
        }

        // Pad if less than 3 characters
        return String.format("%-3s", clean)
                     .replace(' ', 'X');
    }

    // 🚫 Prevent instantiation
    private LocationCodeUtil() {}
}
