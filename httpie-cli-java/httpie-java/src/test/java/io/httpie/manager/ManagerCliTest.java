package io.httpie.manager;

public class ManagerCliTest {
    public static void main(String[] args) {
        testMissingSubcommand();
        System.out.println("ManagerCliTest passed!");
    }

    static void testMissingSubcommand() {
        String msg = ManagerCli.missingSubcommand();
        if (!msg.contains("Please specify")) throw new AssertionError("Expected missing subcommand message");
    }
}
