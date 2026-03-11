package io.httpie.legacy;

import java.util.*;

public class LegacyFormatTest {
    static class MockSession implements V310SessionCookieFormat.SessionDelegate, V320SessionHeaderFormat.SessionDelegate {
        String lastWarning;
        @Override public String getBoundHost() { return "example.com"; }
        @Override public String getSessionId() { return "session123"; }
        @Override public boolean isAnonymous() { return false; }
        @Override public void warnLegacyUsage(String message) { lastWarning = message; }
    }

    public static void main(String[] args) {
        testV310CookieFormat();
        testV320HeaderFormat();
        System.out.println("LegacyFormatTest passed!");
    }

    static void testV310CookieFormat() {
        MockSession session = new MockSession();
        Map<String, Map<String, Object>> cookies = new HashMap<>();
        Map<String, Object> cookieData = new HashMap<>();
        cookieData.put("value", "val1");
        cookies.put("cookie1", cookieData);

        List<Map<String, Object>> normalized = V310SessionCookieFormat.preProcess(session, cookies);
        if (normalized.size() != 1) throw new AssertionError("Expected 1 cookie");
        if (!"cookie1".equals(normalized.get(0).get("name"))) throw new AssertionError("Expected cookie1 name");
        if (session.lastWarning == null) throw new AssertionError("Expected warning for old style cookies");

        Object processed = V310SessionCookieFormat.postProcess(normalized, Map.class);
        if (!(processed instanceof Map)) throw new AssertionError("Expected Map output");
        Map<String, Map<String, Object>> result = (Map<String, Map<String, Object>>) processed;
        if (!result.containsKey("cookie1")) throw new AssertionError("Expected cookie1 in result");
    }

    static void testV320HeaderFormat() {
        MockSession session = new MockSession();
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Test", "value");

        List<Map.Entry<String, String>> normalized = V320SessionHeaderFormat.preProcess(session, headers);
        if (normalized.size() != 1) throw new AssertionError("Expected 1 header");
        if (!"X-Test".equals(normalized.get(0).getKey())) throw new AssertionError("Expected X-Test key");
        if (session.lastWarning == null) throw new AssertionError("Expected warning for old style headers");

        Object processed = V320SessionHeaderFormat.postProcess(normalized, Map.class);
        if (!(processed instanceof Map)) throw new AssertionError("Expected Map output");
        Map<String, String> result = (Map<String, String>) processed;
        if (!"value".equals(result.get("X-Test"))) throw new AssertionError("Expected value for X-Test");
    }
}
