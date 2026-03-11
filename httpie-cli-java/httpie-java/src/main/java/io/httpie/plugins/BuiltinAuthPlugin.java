package io.httpie.plugins;

public abstract class BuiltinAuthPlugin extends AuthPlugin {
    public BuiltinAuthPlugin() {
        this.packageName = "(builtin)";
    }
}
