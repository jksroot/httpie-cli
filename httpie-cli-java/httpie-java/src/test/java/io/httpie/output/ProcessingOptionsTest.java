package io.httpie.output;

public class ProcessingOptionsTest {
    public static void main(String[] args) {
        testShowTraceback();
        System.out.println("ProcessingOptionsTest passed!");
    }

    static void testShowTraceback() {
        ProcessingOptions options = new ProcessingOptions();
        if (options.showTraceback()) throw new AssertionError("Expected false");
        options.debug = true;
        if (!options.showTraceback()) throw new AssertionError("Expected true");
    }
}
