package io.httpie.config;

import java.nio.file.Path;

public class ConfigTest {
    public static void main(String[] args) throws Exception {
        Path tmp = java.nio.file.Files.createTempDirectory("httpie-config");
        Config config = new Config(tmp);
        config.save();
        config.load();
        assert config.defaultOptions().length == 0;
        System.out.println("ConfigTest passed");
    }
}
