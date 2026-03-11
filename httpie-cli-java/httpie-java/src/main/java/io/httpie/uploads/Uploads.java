package io.httpie.uploads;

import io.httpie.context.Environment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Uploads {
    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int n;
        while ((n = inputStream.read(data)) != -1) {
            buffer.write(data, 0, n);
        }
        return buffer.toByteArray();
    }

    public static byte[] prepareRequestBody(Environment env, Object rawBody) throws IOException {
        if (rawBody == null) {
            return new byte[0];
        }
        if (rawBody instanceof byte[]) {
            return (byte[]) rawBody;
        }
        if (rawBody instanceof String) {
            return ((String) rawBody).getBytes(StandardCharsets.UTF_8);
        }
        if (rawBody instanceof InputStream) {
            return readAllBytes((InputStream) rawBody);
        }
        return rawBody.toString().getBytes(StandardCharsets.UTF_8);
    }
}
