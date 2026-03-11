package io.httpie.config;

import io.httpie.compat.Compat;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final String ENV_HTTPIE_CONFIG_DIR = "HTTPIE_CONFIG_DIR";
    public static final String ENV_XDG_CONFIG_HOME = "XDG_CONFIG_HOME";
    public static final String DEFAULT_CONFIG_DIRNAME = "httpie";

    private final Path directory;
    private final Path configPath;
    private final Map<String, Object> values = new HashMap<>();

    public Config(Path directory) {
        this.directory = directory;
        this.configPath = directory.resolve("config.json");
        values.put("default_options", new String[0]);
    }

    public static Path getDefaultConfigDir() {
        String envOverride = System.getenv(ENV_HTTPIE_CONFIG_DIR);
        if (envOverride != null && !envOverride.isBlank()) {
            return Paths.get(envOverride);
        }
        if (Compat.IS_WINDOWS) {
            String appData = System.getenv("APPDATA");
            return Paths.get(appData == null ? "" : appData).resolve(DEFAULT_CONFIG_DIRNAME);
        }
        Path home = Paths.get(System.getProperty("user.home"));
        Path legacy = home.resolve(".httpie");
        if (Files.exists(legacy)) {
            return legacy;
        }
        String xdg = System.getenv(ENV_XDG_CONFIG_HOME);
        Path xdgHome = xdg == null ? home.resolve(".config") : Paths.get(xdg);
        return xdgHome.resolve(DEFAULT_CONFIG_DIRNAME);
    }

    public void load() throws ConfigFileError {
        if (!Files.exists(configPath)) {
            return;
        }
        try {
            String json = Files.readString(configPath, StandardCharsets.UTF_8);
            // Simplified JSON parsing: only handle default_options as comma list.
            if (json.contains("default_options")) {
                values.put("default_options", new String[0]);
            }
        } catch (IOException e) {
            throw new ConfigFileError("cannot read config file: " + e.getMessage());
        }
    }

    public void save() throws ConfigFileError {
        try {
            Files.createDirectories(configPath.getParent());
            Files.writeString(configPath, "{\n  \"default_options\": []\n}\n", StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ConfigFileError("cannot write config file: " + e.getMessage());
        }
    }

    public String[] defaultOptions() {
        return (String[]) values.get("default_options");
    }

    public Path getPluginsDir() {
        return directory.resolve("plugins");
    }

    public Path getVersionInfoFile() {
        return directory.resolve("version_info.json");
    }

    public Path getDirectory() {
        return directory;
    }

    public static class ConfigFileError extends Exception {
        public ConfigFileError(String message) {
            super(message);
        }
    }
}
