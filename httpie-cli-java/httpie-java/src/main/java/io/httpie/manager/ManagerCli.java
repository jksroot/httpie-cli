package io.httpie.manager;

import java.util.*;

public class ManagerCli {
    public static class CommandDefinition {
        public final String name;
        public final String description;
        public final List<String> subcommands;

        public CommandDefinition(String name, String description, List<String> subcommands) {
            this.name = name;
            this.description = description;
            this.subcommands = subcommands;
        }
    }

    public static final Map<String, CommandDefinition> COMMANDS = new LinkedHashMap<>();

    static {
        COMMANDS.put("cli", new CommandDefinition("cli", "Manage HTTPie for Terminal",
                Arrays.asList("export-args", "check-updates", "sessions")));
        COMMANDS.put("plugins", new CommandDefinition("plugins", "Manage HTTPie plugins",
                Arrays.asList("install", "upgrade", "uninstall", "list")));
    }

    public static String missingSubcommand(String... args) {
        Map<String, CommandDefinition> base = COMMANDS;
        for (String arg : args) {
            if (base.containsKey(arg)) {
                base = Collections.emptyMap();
            }
        }
        String subcommands = String.join(", ", base.keySet());
        return "Please specify one of these: " + subcommands;
    }
}
