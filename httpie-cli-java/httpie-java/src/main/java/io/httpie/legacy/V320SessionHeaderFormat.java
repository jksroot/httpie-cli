package io.httpie.legacy;

import java.util.*;

public class V320SessionHeaderFormat {
    public static final String OLD_HEADER_STORE_WARNING = 
        "Outdated layout detected for the current session. Please consider updating it,\n" +
        "in order to use the latest features regarding the header layout.\n\n" +
        "For fixing the current session:\n\n" +
        "    $ httpie cli sessions upgrade %s %s\n";

    public static final String OLD_HEADER_STORE_WARNING_FOR_NAMED_SESSIONS = 
        "\nFor fixing all named sessions:\n\n" +
        "    $ httpie cli sessions upgrade-all\n";

    public static final String OLD_HEADER_STORE_LINK = "\nSee $INSERT_LINK for more information.";

    public interface SessionDelegate {
        String getBoundHost();
        String getSessionId();
        boolean isAnonymous();
        void warnLegacyUsage(String message);
    }

    public static List<Map.Entry<String, String>> preProcess(SessionDelegate session, Object headers) {
        List<Map.Entry<String, String>> normalizedHeaders = new ArrayList<>();
        boolean isOldStyle = headers instanceof Map;

        if (isOldStyle) {
            Map<String, String> headerMap = (Map<String, String>) headers;
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                normalizedHeaders.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
            }
        } else {
            List<Map<String, String>> headerList = (List<Map<String, String>>) headers;
            for (Map<String, String> item : headerList) {
                normalizedHeaders.add(new AbstractMap.SimpleEntry<>(item.get("name"), item.get("value")));
            }
        }

        if (isOldStyle) {
            String warning = String.format(OLD_HEADER_STORE_WARNING, session.getBoundHost(), session.getSessionId());
            if (!session.isAnonymous()) {
                warning += OLD_HEADER_STORE_WARNING_FOR_NAMED_SESSIONS;
            }
            warning += OLD_HEADER_STORE_LINK;
            session.warnLegacyUsage(warning);
        }

        return normalizedHeaders;
    }

    public static Object postProcess(List<Map.Entry<String, String>> normalizedHeaders, Class<?> originalType) {
        if (Map.class.isAssignableFrom(originalType)) {
            Map<String, String> result = new LinkedHashMap<>();
            for (Map.Entry<String, String> item : normalizedHeaders) {
                result.put(item.getKey(), item.getValue());
            }
            return result;
        } else {
            List<Map<String, String>> result = new ArrayList<>();
            for (Map.Entry<String, String> item : normalizedHeaders) {
                Map<String, String> map = new HashMap<>();
                map.put("name", item.getKey());
                map.put("value", item.getValue());
                result.add(map);
            }
            return result;
        }
    }
}
