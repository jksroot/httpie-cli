package io.httpie.plugins;

import java.util.Map;

public abstract class FormatterPlugin extends BasePlugin {
    protected String groupName;
    protected boolean enabled = true;

    public FormatterPlugin(Map<String, Object> options) {
        // Options handling logic if needed
    }

    public String getGroupName() { return groupName; }
    public boolean isEnabled() { return enabled; }

    public abstract String formatHeaders(String headers);
    public abstract String formatBody(String body, String contentType);
}
