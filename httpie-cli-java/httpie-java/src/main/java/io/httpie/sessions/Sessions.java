package io.httpie.sessions;

import io.httpie.context.Environment;
import io.httpie.cli.Dicts;

import java.nio.file.Path;

public class Sessions {
    public static final String SESSIONS_DIR_NAME = "sessions";

    public static boolean isAnonymousSession(String sessionName) {
        return sessionName.contains("/");
    }

    public static String sessionHostnameToDirname(String hostname, String sessionName) {
        return SESSIONS_DIR_NAME + "/" + hostname.replace(":", "_") + "/" + sessionName + ".json";
    }

    public static String stripPort(String hostname) {
        int idx = hostname.indexOf(":");
        return idx == -1 ? hostname : hostname.substring(0, idx);
    }

    public static class Session {
        private final Path path;
        private final Environment env;
        private Dicts.HTTPHeadersDict headers = new Dicts.HTTPHeadersDict();

        public Session(Path path, Environment env) {
            this.path = path;
            this.env = env;
        }

        public Dicts.HTTPHeadersDict headers() {
            return headers;
        }

        public Path path() {
            return path;
        }
    }
}
