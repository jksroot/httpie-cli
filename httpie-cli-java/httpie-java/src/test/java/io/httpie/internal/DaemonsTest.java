package io.httpie.internal;

public class DaemonsTest {
    public static void main(String[] args) {
        testIsDaemonMode();
        System.out.println("DaemonsTest passed!");
    }

    static void testIsDaemonMode() {
        if (!Daemons.isDaemonMode(new String[]{"--daemon"})) throw new AssertionError("Expected true for --daemon");
        if (Daemons.isDaemonMode(new String[]{"--help"})) throw new AssertionError("Expected false for --help");
    }
}
