package io.httpie.output;

public class OutputUtilsTest {
    public static void main(String[] args) {
        testParsePrefixedJson();
        System.out.println("OutputUtilsTest passed!");
    }

    static void testParsePrefixedJson() {
        String[] result = OutputUtils.parsePrefixedJson("')]}')){\"a\":1}");
        if (!result[0].equals("')]}'))")) throw new AssertionError("Expected prefix");
        if (!result[1].startsWith("{\"a\"")) throw new AssertionError("Expected json body");
    }
}
