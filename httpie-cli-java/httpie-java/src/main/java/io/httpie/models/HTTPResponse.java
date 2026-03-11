package io.httpie.models;

import java.net.http.HttpResponse;

public class HTTPResponse extends HTTPMessage {
    private final HttpResponse<byte[]> response;

    public HTTPResponse(HttpResponse<byte[]> response) {
        this.response = response;
    }

    @Override
    public String getHeaders() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/").append(response.version()).append(" ").append(response.statusCode()).append("\r\n");
        response.headers().map().forEach((name, values) -> {
            for (String value : values) {
                sb.append(name).append(": ").append(value).append("\r\n");
            }
        });
        return sb.toString().trim();
    }

    @Override
    public byte[] getBody() {
        return response.body();
    }

    @Override
    public String getContentType() {
        return response.headers().firstValue("Content-Type").orElse("");
    }
}
