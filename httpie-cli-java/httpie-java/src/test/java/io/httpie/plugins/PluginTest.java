package io.httpie.plugins;

import java.net.http.HttpRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class PluginTest {
    public static void main(String[] args) {
        testPluginManager();
        testBasicAuthPlugin();
        testBearerAuthPlugin();
        System.out.println("All plugin tests passed!");
    }

    private static void testPluginManager() {
        PluginManager manager = new PluginManager();
        BasicAuthPlugin basic = new BasicAuthPlugin();
        manager.register(basic);

        List<AuthPlugin> authPlugins = manager.getAuthPlugins();
        assert authPlugins.size() == 1;
        assert authPlugins.get(0) instanceof BasicAuthPlugin;
        assert manager.getAuthPlugin("basic") == basic;
        
        System.out.println("testPluginManager passed");
    }

    private static void testBasicAuthPlugin() {
        BasicAuthPlugin plugin = new BasicAuthPlugin();
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create("http://example.com"));
        plugin.prepareRequest(builder, "user", "pass");
        HttpRequest request = builder.build();
        
        String authHeader = request.headers().firstValue("Authorization").orElse("");
        assert authHeader.startsWith("Basic ");
        // user:pass -> dXNlcjpwYXNz
        assert authHeader.equals("Basic dXNlcjpwYXNz");
        
        System.out.println("testBasicAuthPlugin passed");
    }

    private static void testBearerAuthPlugin() {
        BearerAuthPlugin plugin = new BearerAuthPlugin();
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create("http://example.com"));
        plugin.prepareRequest(builder, "my-token", null);
        HttpRequest request = builder.build();
        
        String authHeader = request.headers().firstValue("Authorization").orElse("");
        assert authHeader.equals("Bearer my-token");

        // Test with rawAuth
        plugin.setRawAuth("raw-token");
        builder = HttpRequest.newBuilder().uri(URI.create("http://example.com"));
        plugin.prepareRequest(builder, null, null);
        request = builder.build();
        assert request.headers().firstValue("Authorization").orElse("").equals("Bearer raw-token");
        
        System.out.println("testBearerAuthPlugin passed");
    }
}
