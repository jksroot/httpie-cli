package io.httpie.output.lexers;

public class LexersTest {
    public static void main(String[] args) {
        testCommon();
        testJson();
        System.out.println("LexersTest passed!");
    }

    static void testCommon() {
        if (!"x".equals(CommonLexer.tokenize("x"))) throw new AssertionError("Expected same");
    }

    static void testJson() {
        if (!"{}".equals(JsonLexer.tokenize("{}"))) throw new AssertionError("Expected same json");
    }
}
