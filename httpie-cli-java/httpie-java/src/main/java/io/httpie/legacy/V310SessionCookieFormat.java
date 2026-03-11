package io.httpie.legacy;

import java.util.*;

public class V310SessionCookieFormat {
    public static final String INSECURE_COOKIE_JAR_WARNING = 
        "Outdated layout detected for the current session. Please consider updating it,\n" +
        "in order to not get affected by potential security problems.\n\n" +
        "For fixing the current session:\n\n" +
        "    With binding all cookies to the current host (secure):\n" +
        "        $ httpie cli sessions upgrade --bind-cookies %s %s\n\n" +
        "    Without binding cookies (leaving them as is) (insecure):\n" +
        "        $ httpie cli sessions upgrade %s %s\n";

    public static final String INSECURE_COOKIE_JAR_WARNING_FOR_NAMED_SESSIONS = 
        "\nFor fixing all named sessions:\n\n" +
        "    With binding all cookies to the current host (secure):\n" +
        "        $ httpie cli sessions upgrade-all --bind-cookies\n\n" +
        "    Without binding cookies (leaving them as is) (insecure):\n" +
        "        $ httpie cli sessions upgrade-all\n";

    public static final String INSECURE_COOKIE_SECURITY_LINK = "\nSee https://pie.co/docs/security for more information.";

    public interface SessionDelegate {
        String getBoundHost();
        String getSessionId();
        boolean isAnonymous();
        void warnLegacyUsage(String message);
    }

    public static List<Map<String, Object>> preProcess(SessionDelegate session, Object cookies) {
        List<Map<String, Object>> normalizedCookies;
        boolean isOldStyle = cookies instanceof Map;

        if (isOldStyle) {
            normalizedCookies = new ArrayList<>();
            Map<String, Map<String, Object>> cookieMap = (Map<String, Map<String, Object>>) cookies;
            for (Map.Entry<String, Map<String, Object>> entry : cookieMap.entrySet()) {
                Map<String, Object> cookie = new HashMap<>(entry.getValue());
                cookie.put("name", entry.getKey());
                normalizedCookies.add(cookie);
            }
        } else {
            normalizedCookies = (List<Map<String, Object>>) cookies;
        }

        boolean shouldIssueWarning = isOldStyle && normalizedCookies.stream().anyMatch(c -> "".equals(c.getOrDefault("domain", "")));

        if (shouldIssueWarning) {
            String warning = String.format(INSECURE_COOKIE_JAR_WARNING, session.getBoundHost(), session.getSessionId(), session.getBoundHost(), session.getSessionId());
            if (!session.isAnonymous()) {
                warning += INSECURE_COOKIE_JAR_WARNING_FOR_NAMED_SESSIONS;
            }
            warning += INSECURE_COOKIE_SECURITY_LINK;
            session.warnLegacyUsage(warning);
        }

        return normalizedCookies;
    }

    public static Object postProcess(List<Map<String, Object>> normalizedCookies, Class<?> originalType) {
        if (Map.class.isAssignableFrom(originalType)) {
            Map<String, Map<String, Object>> result = new LinkedHashMap<>();
            for (Map<String, Object> cookie : normalizedCookies) {
                Map<String, Object> cookieCopy = new HashMap<>(cookie);
                String name = (String) cookieCopy.remove("name");
                result.put(name, cookieCopy);
            }
            return result;
        } else {
            return normalizedCookies;
        }
    }
}
