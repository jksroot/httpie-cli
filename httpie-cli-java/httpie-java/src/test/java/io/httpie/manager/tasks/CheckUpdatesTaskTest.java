package io.httpie.manager.tasks;

public class CheckUpdatesTaskTest {
    public static void main(String[] args) {
        testRun();
        System.out.println("CheckUpdatesTaskTest passed!");
    }

    static void testRun() {
        String msg = CheckUpdatesTask.run();
        if (!msg.contains("up-to-date")) throw new AssertionError("Expected up-to-date message");
    }
}
