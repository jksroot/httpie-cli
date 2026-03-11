package io.httpie.models;

import io.httpie.utils.HttpieUtils;
import java.util.Map;

public abstract class HTTPMessage {
    public abstract String getHeaders();
    public abstract byte[] getBody();
    public abstract String getContentType();

    public String getEncoding() {
        HttpieUtils.ContentTypeInfo info = HttpieUtils.parseContentTypeHeader(getContentType());
        return info.params().getOrDefault("charset", "");
    }
}
