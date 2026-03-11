package io.httpie.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Daemons {
    public static void spawnDaemon(String task) {
        // In Java, we can't easily double-fork. 
        // We'll start a new process with a special flag.
        List<String> cmd = new ArrayList<>();
        cmd.add("java");
        cmd.add("-cp");
        cmd.add(System.getProperty("java.class.path"));
        cmd.add("io.httpie.cli.HttpieCli");
        cmd.add(task);
        cmd.add("--daemon");

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectError(ProcessBuilder.Redirect.DISCARD);
        pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
        try {
            pb.start();
        } catch (IOException e) {
            // Ignore errors in daemon spawning
        }
    }

    public static boolean isDaemonMode(String[] args) {
        for (String arg : args) {
            if ("--daemon".equals(arg)) return true;
        }
        return false;
    }
}
