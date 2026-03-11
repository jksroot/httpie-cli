package io.httpie.manager.tasks;

import java.util.List;

public class PluginsTask {
    public static String install(List<String> targets) {
        return "Installing " + String.join(", ", targets) + "...";
    }

    public static String upgrade(List<String> targets) {
        return "Upgrading " + String.join(", ", targets) + "...";
    }

    public static String uninstall(List<String> targets) {
        return "Uninstalling " + String.join(", ", targets) + "...";
    }

    public static String list() {
        return "No plugins installed.";
    }
}
