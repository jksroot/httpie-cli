package io.httpie.plugins;

import java.util.*;
import java.util.stream.Collectors;

public class PluginManager {
    private final List<BasePlugin> plugins = new ArrayList<>();

    public void register(BasePlugin... newPlugins) {
        Collections.addAll(plugins, newPlugins);
    }

    public void unregister(BasePlugin plugin) {
        plugins.remove(plugin);
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePlugin> List<T> filter(Class<T> type) {
        return plugins.stream()
                .filter(type::isInstance)
                .map(p -> (T) p)
                .collect(Collectors.toList());
    }

    public List<AuthPlugin> getAuthPlugins() {
        return filter(AuthPlugin.class);
    }

    public Map<String, AuthPlugin> getAuthPluginMapping() {
        return getAuthPlugins().stream()
                .collect(Collectors.toMap(AuthPlugin::getAuthType, p -> p));
    }

    public AuthPlugin getAuthPlugin(String authType) {
        return getAuthPluginMapping().get(authType);
    }

    public List<FormatterPlugin> getFormatters() {
        return filter(FormatterPlugin.class);
    }

    public Map<String, List<FormatterPlugin>> getFormattersGrouped() {
        return getFormatters().stream()
                .collect(Collectors.groupingBy(FormatterPlugin::getGroupName));
    }

    @Override
    public String toString() {
        return "PluginManager{" +
                "auth=" + getAuthPlugins().stream().map(BasePlugin::getName).toList() +
                ", formatters=" + getFormatters().stream().map(BasePlugin::getName).toList() +
                '}';
    }
}
