package io.httpie.utils;

import java.util.*;
import java.util.regex.Pattern;

public class HttpieUtils {
    private static final Pattern RE_COOKIE_SPLIT = Pattern.compile(", (?=[^ ;]+=)");

    public static List<String> splitCookies(String cookies) {
        if (cookies == null || cookies.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(RE_COOKIE_SPLIT.split(cookies));
    }

    public static ContentTypeInfo parseContentTypeHeader(String header) {
        if (header == null) {
            return new ContentTypeInfo("", Collections.emptyMap());
        }
        String[] tokens = header.split(";");
        String contentType = tokens[0].trim();
        Map<String, String> params = new HashMap<>();
        String itemsToStrip = "\"' ";

        for (int i = 1; i < tokens.length; i++) {
            String param = tokens[i].trim();
            if (!param.isEmpty()) {
                int indexOfEquals = param.indexOf("=");
                if (indexOfEquals != -1) {
                    String key = strip(param.substring(0, indexOfEquals).trim(), itemsToStrip);
                    String value = strip(param.substring(indexOfEquals + 1).trim(), itemsToStrip);
                    params.put(key.toLowerCase(), value);
                } else {
                    params.put(param.toLowerCase(), "true");
                }
            }
        }
        return new ContentTypeInfo(contentType, params);
    }

    private static String strip(String s, String itemsToStrip) {
        int start = 0;
        while (start < s.length() && itemsToStrip.indexOf(s.charAt(start)) != -1) {
            start++;
        }
        int end = s.length();
        while (end > start && itemsToStrip.indexOf(s.charAt(end - 1)) != -1) {
            end--;
        }
        return s.substring(start, end);
    }

    public record ContentTypeInfo(String contentType, Map<String, String> params) {}

    public static String humanizeBytes(long n) {
        if (n == 1) return "1 B";
        String[] suffixes = {"B", "kB", "MB", "GB", "TB", "PB"};
        double count = n;
        int i = 0;
        while (count >= 1024 && i < suffixes.length - 1) {
            count /= 1024;
            i++;
        }
        return String.format("%.2f %s", count, suffixes[i]);
    }
}
