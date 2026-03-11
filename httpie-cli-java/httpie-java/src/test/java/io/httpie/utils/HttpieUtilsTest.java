package io.httpie.utils;

import java.util.List;

public class HttpieUtilsTest {

    public static void main(String[] args) {
        testSplitCookies();
        testParseContentTypeHeader();
        testHumanizeBytes();
        System.out.println("HttpieUtilsTest passed!");
    }

    static void testSplitCookies() {
        String cookies = "name1=value1, name2=value2; Path=/";
        List<String> result = HttpieUtils.splitCookies(cookies);
        if (result.size() != 2) throw new AssertionError("Expected size 2, got " + result.size());
        if (!result.get(0).equals("name1=value1")) throw new AssertionError("Expected name1=value1, got " + result.get(0));
        if (!result.get(1).equals("name2=value2; Path=/")) throw new AssertionError("Expected name2=value2; Path=/, got " + result.get(1));
    }

    static void testParseContentTypeHeader() {
        String header = "application/json; charset=utf-8; profile=\"http://example.com\"";
        HttpieUtils.ContentTypeInfo info = HttpieUtils.parseContentTypeHeader(header);
        if (!info.contentType().equals("application/json")) throw new AssertionError("Expected application/json, got " + info.contentType());
        if (!info.params().get("charset").equals("utf-8")) throw new AssertionError("Expected utf-8, got " + info.params().get("charset"));
        if (!info.params().get("profile").equals("http://example.com")) throw new AssertionError("Expected http://example.com, got " + info.params().get("profile"));
    }

    static void testHumanizeBytes() {
        if (!HttpieUtils.humanizeBytes(1).equals("1 B")) throw new AssertionError("Expected 1 B, got " + HttpieUtils.humanizeBytes(1));
        if (!HttpieUtils.humanizeBytes(1024).equals("1.00 kB")) throw new AssertionError("Expected 1.00 kB, got " + HttpieUtils.humanizeBytes(1024));
        if (!HttpieUtils.humanizeBytes(1024 * 1024).equals("1.00 MB")) throw new AssertionError("Expected 1.00 MB, got " + HttpieUtils.humanizeBytes(1024 * 1024));
    }
}
