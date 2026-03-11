package io.httpie.plugins;

import java.net.http.HttpRequest;
import java.util.Base64;

public class BasicAuthPlugin extends BuiltinAuthPlugin {
    public BasicAuthPlugin() {
        this.name = "Basic HTTP auth";
        this.authType = "basic";
        this.netrcParse = true;
    }

    @Override
    public HttpRequest.Builder prepareRequest(HttpRequest.Builder builder, String username, String password) {
        String credentials = username + ":" + (password == null ? "" : password);
        String token = Base64.getEncoder().encodeToString(credentials.getBytes());
        return builder.header("Authorization", "Basic " + token);
    }
}
