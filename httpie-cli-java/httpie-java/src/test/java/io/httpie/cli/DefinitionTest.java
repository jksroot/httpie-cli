package io.httpie.cli;

public class DefinitionTest {
    public static void main(String[] args) {
        testOptionsExist();
        System.out.println("DefinitionTest passed!");
    }

    static void testOptionsExist() {
        if (Definition.OPTIONS.isEmpty()) throw new AssertionError("Expected non-empty options");
        boolean foundJson = false;
        for (Definition.Option opt : Definition.OPTIONS) {
            if ("--json".equals(opt.name)) {
                foundJson = true;
                break;
            }
        }
        if (!foundJson) throw new AssertionError("Expected --json option to exist");
    }
}
