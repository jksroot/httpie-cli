package io.httpie.plugins;

import java.net.http.HttpRequest;

public class BearerAuthPlugin extends BuiltinAuthPlugin {
    public BearerAuthPlugin() {
        this.name = "Bearer HTTP Auth";
        this.authType = "bearer";
        this.netrcParse = false;
        this.authParse = false;
    }

    @Override
    public HttpRequest.Builder prepareRequest(HttpRequest.Builder builder, String username, String password) {
        // For bearer, the "username" field in RequestItems is usually the token
        // if authParse is false, rawAuth is used.
        String token = (username != null) ? username : rawAuth;
        return builder.header("Authorization", "Bearer " + token);
    }
}
