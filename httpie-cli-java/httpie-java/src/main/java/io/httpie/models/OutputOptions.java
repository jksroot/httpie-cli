package io.httpie.models;

public class OutputOptions {
    public boolean headers;
    public boolean body;
    public boolean meta;

    public OutputOptions(boolean headers, boolean body, boolean meta) {
        this.headers = headers;
        this.body = body;
        this.meta = meta;
    }

    public boolean any() {
        return headers || body || meta;
    }
}
