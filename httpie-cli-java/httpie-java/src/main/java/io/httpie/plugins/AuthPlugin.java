package io.httpie.plugins;

import java.net.http.HttpRequest;
import java.util.Optional;

public abstract class AuthPlugin extends BasePlugin {
    protected String authType;
    protected boolean authRequire = true;
    protected boolean authParse = true;
    protected boolean netrcParse = false;
    protected boolean promptPassword = true;
    protected String rawAuth;

    public String getAuthType() { return authType; }
    public boolean isAuthRequire() { return authRequire; }
    public boolean isAuthParse() { return authParse; }
    public boolean isNetrcParse() { return netrcParse; }
    public boolean isPromptPassword() { return promptPassword; }
    public void setRawAuth(String rawAuth) { this.rawAuth = rawAuth; }

    public abstract java.net.http.HttpRequest.Builder prepareRequest(
        java.net.http.HttpRequest.Builder builder,
        String username,
        String password
    );
}
