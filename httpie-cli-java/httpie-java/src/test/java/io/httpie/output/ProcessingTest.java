package io.httpie.output;

import java.util.List;

public class ProcessingTest {
    public static void main(String[] args) {
        testIsValidMime();
        testFormatting();
        System.out.println("ProcessingTest passed!");
    }

    static void testIsValidMime() {
        if (!Processing.isValidMime("application/json")) throw new AssertionError("Expected valid mime");
        if (Processing.isValidMime("invalid")) throw new AssertionError("Expected invalid mime");
    }

    static void testFormatting() {
        Processing.Formatting formatting = new Processing.Formatting(List.of("format"));
        if (!"abc".equals(formatting.formatHeaders("abc"))) throw new AssertionError("Expected same headers");
    }
}
