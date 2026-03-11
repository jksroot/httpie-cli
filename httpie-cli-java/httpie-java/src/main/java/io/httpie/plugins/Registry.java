package io.httpie.plugins;

public class Registry {
    private static final PluginManager pluginManager = new PluginManager();

    static {
        pluginManager.register(
            new BasicAuthPlugin(),
            new BearerAuthPlugin()
            // Add other builtin plugins as they are refactored
        );
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }
}
