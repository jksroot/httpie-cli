package io.httpie.output;

import io.httpie.models.HTTPMessage;
import io.httpie.models.OutputOptions;
import io.httpie.output.streams.RawStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class OutputWriter {
    public static byte[] writeMessage(HTTPMessage message, OutputOptions options) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        RawStream stream = new RawStream(message, options);
        for (byte[] chunk : stream) {
            out.write(chunk);
        }
        return out.toByteArray();
    }
}
