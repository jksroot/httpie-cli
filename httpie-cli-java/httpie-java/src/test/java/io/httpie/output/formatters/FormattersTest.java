package io.httpie.output.formatters;

public class FormattersTest {
    public static void main(String[] args) {
        testColors();
        testHeaders();
        System.out.println("FormattersTest passed!");
    }

    static void testColors() {
        if (!"hi".equals(ColorsFormatter.format("hi"))) throw new AssertionError("Expected same string");
    }

    static void testHeaders() {
        if (!"h".equals(HeadersFormatter.format("h"))) throw new AssertionError("Expected same headers");
    }
}
