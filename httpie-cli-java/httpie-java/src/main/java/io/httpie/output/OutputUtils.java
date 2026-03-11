package io.httpie.output;

public class OutputUtils {
    public static String[] parsePrefixedJson(String data) {
        String prefix = "";
        if (data.startsWith("')]}'))")) {
            prefix = "')]}'))";
        }
        String body = data.substring(prefix.length());
        return new String[]{prefix, body};
    }
}
