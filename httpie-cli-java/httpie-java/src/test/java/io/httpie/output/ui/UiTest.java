package io.httpie.output.ui;

public class UiTest {
    public static void main(String[] args) {
        testManPages();
        testRichUtils();
        System.out.println("UiTest passed!");
    }

    static void testManPages() {
        if (!ManPages.hint().contains("man")) throw new AssertionError("Expected man hint");
    }

    static void testRichUtils() {
        if (!RichUtils.emphasize("x").contains("**")) throw new AssertionError("Expected emphasis");
    }
}
