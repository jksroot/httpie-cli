package io.httpie.client;

import io.httpie.cli.ArgumentParser;
import io.httpie.cli.HttpieCli;
import io.httpie.models.HTTPResponse;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpieClient {
    private final HttpClient client;

    public HttpieClient() {
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    public HTTPResponse send(HttpieCli cli) throws Exception {
        ArgumentParser.ParsedArgs options = cli.args;
        String url = options.url;
        
        List<Map.Entry<String, Object>> params = options.requestItems.params.items();
        if (!params.isEmpty()) {
            String queryString = params.stream()
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                            URLEncoder.encode(String.valueOf(e.getValue()), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            url += (url.contains("?") ? "&" : "?") + queryString;
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(options.method, buildBodyPublisher(options));

        options.requestItems.headers.items().forEach(e -> {
            if (e.getValue() != null) {
                requestBuilder.header(e.getKey(), String.valueOf(e.getValue()));
            }
        });
        
        // Ensure User-Agent
        boolean hasUserAgent = options.requestItems.headers.items().stream()
                .anyMatch(e -> e.getKey().equalsIgnoreCase("user-agent"));
        if (!hasUserAgent) {
            requestBuilder.header("User-Agent", "HTTPie-Java/1.0");
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return new HTTPResponse(response);
    }

    private HttpRequest.BodyPublisher buildBodyPublisher(ArgumentParser.ParsedArgs options) {
        Map<String, List<Object>> data = options.requestItems.data instanceof Map ? (Map)options.requestItems.data : ((io.httpie.cli.Dicts.BaseMultiDict)options.requestItems.data).getData();
        
        if (data.isEmpty()) {
            return HttpRequest.BodyPublishers.noBody();
        }

        if (options.form) {
            String formBody = options.requestItems.data instanceof io.httpie.cli.Dicts.BaseMultiDict ?
                ((io.httpie.cli.Dicts.BaseMultiDict)options.requestItems.data).items().stream()
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                            URLEncoder.encode(String.valueOf(e.getValue()), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&")) : "";
            return HttpRequest.BodyPublishers.ofString(formBody);
        } else {
            String jsonBody = "{" + data.entrySet().stream()
                    .map(e -> "\"" + e.getKey() + "\": \"" + e.getValue().get(0) + "\"") // Simplified
                    .collect(Collectors.joining(", ")) + "}";
            return HttpRequest.BodyPublishers.ofString(jsonBody);
        }
    }
}
