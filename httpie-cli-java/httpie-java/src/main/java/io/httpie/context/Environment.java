package io.httpie.context;

import io.httpie.config.Config;
import io.httpie.config.Config.ConfigFileError;
import io.httpie.compat.Compat;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Environment {
    public enum LogLevel { INFO, WARNING, ERROR }

    public final boolean isWindows = Compat.IS_WINDOWS;
    public InputStream stdin = System.in;
    public PrintStream stdout = System.out;
    public PrintStream stderr = System.err;
    public String stdinEncoding = StandardCharsets.UTF_8.name();
    public String stdoutEncoding = StandardCharsets.UTF_8.name();
    public String programName = "http";
    public boolean showDisplays = true;

    private Config config;
    private final Path configDir;

    public Environment(Path configDir) {
        this.configDir = configDir;
    }

    public Config getConfig() {
        if (config == null) {
            config = new Config(configDir);
            try {
                config.load();
            } catch (ConfigFileError e) {
                logError(e.getMessage(), LogLevel.WARNING);
            }
        }
        return config;
    }

    public void logError(String message, LogLevel level) {
        stderr.println(programName + ": " + level.name().toLowerCase() + ": " + message);
    }
}
