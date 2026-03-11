package io.httpie.manager.tasks;

import java.util.Map;

public class SessionsTaskTest {
    public static void main(String[] args) {
        testUpgrade();
        testUpgradeAll();
        System.out.println("SessionsTaskTest passed!");
    }

    static void testUpgrade() {
        String msg = SessionsTask.upgrade("example.com", "session1");
        if (!msg.contains("Upgraded")) throw new AssertionError("Expected upgraded message");
    }

    static void testUpgradeAll() {
        String msg = SessionsTask.upgradeAll(Map.of("s1", "host"));
        if (!msg.contains("1")) throw new AssertionError("Expected count in message");
    }
}
