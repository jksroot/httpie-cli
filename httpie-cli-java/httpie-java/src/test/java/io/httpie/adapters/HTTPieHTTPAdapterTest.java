package io.httpie.adapters;

import io.httpie.cli.Dicts;

import java.util.List;
import java.util.Map;

public class HTTPieHTTPAdapterTest {
    public static void main(String[] args) {
        HTTPieHTTPAdapter adapter = new HTTPieHTTPAdapter();
        Dicts.HTTPHeadersDict dict = adapter.wrapHeaders(Map.of("Set-Cookie", List.of("a=1", "b=2")));
        assert dict.items().size() == 2;
        System.out.println("HTTPieHTTPAdapterTest passed");
    }
}
