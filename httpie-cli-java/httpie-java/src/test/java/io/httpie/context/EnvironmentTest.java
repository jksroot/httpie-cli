package io.httpie.context;

import java.nio.file.Path;

public class EnvironmentTest {
    public static void main(String[] args) {
        Environment env = new Environment(Path.of("."));
        env.logError("test", Environment.LogLevel.INFO);
        assert env.getConfig() != null;
        System.out.println("EnvironmentTest passed");
    }
}
