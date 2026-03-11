package io.httpie.manager.tasks;

import java.util.List;

public class PluginsTaskTest {
    public static void main(String[] args) {
        testInstall();
        testList();
        System.out.println("PluginsTaskTest passed!");
    }

    static void testInstall() {
        String msg = PluginsTask.install(List.of("plugin1"));
        if (!msg.contains("Installing")) throw new AssertionError("Expected install message");
    }

    static void testList() {
        String msg = PluginsTask.list();
        if (!msg.contains("No plugins")) throw new AssertionError("Expected empty list message");
    }
}
