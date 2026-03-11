package io.httpie.models;

import java.net.http.HttpRequest;
import java.util.stream.Collectors;

public class HTTPRequest extends HTTPMessage {
    private final HttpRequest request;
    private final byte[] body;

    public HTTPRequest(HttpRequest request, byte[] body) {
        this.request = request;
        this.body = body != null ? body : new byte[0];
    }

    @Override
    public String getHeaders() {
        StringBuilder sb = new StringBuilder();
        sb.append(request.method()).append(" ").append(request.uri().getPath());
        if (request.uri().getQuery() != null) {
            sb.append("?").append(request.uri().getQuery());
        }
        sb.append(" HTTP/1.1\r\n");

        request.headers().map().forEach((name, values) -> {
            for (String value : values) {
                sb.append(name).append(": ").append(value).append("\r\n");
            }
        });
        return sb.toString().trim();
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    @Override
    public String getContentType() {
        return request.headers().firstValue("Content-Type").orElse("");
    }
}
