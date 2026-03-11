package io.httpie.adapters;

import io.httpie.cli.Dicts;
import java.util.List;
import java.util.Map;

public class HTTPieHTTPAdapter {
    public Dicts.HTTPHeadersDict wrapHeaders(Map<String, List<String>> headers) {
        Dicts.HTTPHeadersDict dict = new Dicts.HTTPHeadersDict();
        if (headers != null) {
            headers.forEach((key, values) -> {
                if (values == null || values.isEmpty()) {
                    dict.add(key, null);
                } else {
                    values.forEach(value -> dict.add(key, value));
                }
            });
        }
        return dict;
    }
}
