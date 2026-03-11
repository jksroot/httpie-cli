package io.httpie.plugins;

public abstract class BasePlugin {
    protected String name;
    protected String description;
    protected String packageName;

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
}
