package io.httpie.cli;

import java.util.Comparator;
import java.util.Optional;

public record RequestItem(String key, String sep, String value, String raw) {
    public static RequestItem parse(String arg) {
        String foundSep = null;
        int foundIdx = -1;

        for (String sep : Constants.SEPARATOR_GROUP_ALL_ITEMS) {
            int idx = arg.indexOf(sep);
            if (idx != -1) {
                if (foundSep == null || idx < foundIdx || (idx == foundIdx && sep.length() > foundSep.length())) {
                    foundSep = sep;
                    foundIdx = idx;
                }
            }
        }

        if (foundSep != null) {
            String key = arg.substring(0, foundIdx);
            String value = arg.substring(foundIdx + foundSep.length());
            return new RequestItem(key, foundSep, value, arg);
        }
        return new RequestItem(null, null, null, arg);
    }
}
