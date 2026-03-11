package io.httpie.output;

import java.util.ArrayList;
import java.util.List;

public class Processing {
    public static boolean isValidMime(String mime) {
        return mime != null && mime.contains("/");
    }

    public static class Conversion {
        public static Object getConverter(String mime) {
            if (isValidMime(mime)) {
                return new Object();
            }
            return null;
        }
    }

    public static class Formatting {
        public List<String> groups = new ArrayList<>();

        public Formatting(List<String> groups) {
            this.groups = groups;
        }

        public String formatHeaders(String headers) {
            return headers;
        }

        public String formatBody(String content, String mime) {
            return content;
        }

        public String formatMetadata(String metadata) {
            return metadata;
        }
    }
}
