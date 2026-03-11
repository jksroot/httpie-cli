package io.httpie.output;

import java.util.List;
import java.util.Map;

public class ProcessingOptions {
    public boolean debug = false;
    public boolean traceback = false;
    public boolean stream = false;
    public String style = "auto";
    public List<String> prettify = List.of("format", "colors");
    public String responseMime = null;
    public String responseCharset = null;
    public boolean json = false;
    public Map<String, Object> formatOptions = Map.of();

    public boolean showTraceback() {
        return debug || traceback;
    }
}
