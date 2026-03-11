package io.httpie.manager.tasks;

import java.util.Map;

public class SessionsTask {
    public static String upgrade(String hostname, String sessionName) {
        return "Upgraded " + sessionName + " @ " + hostname;
    }

    public static String upgradeAll(Map<String, String> sessions) {
        return "Upgraded " + sessions.size() + " sessions";
    }
}
