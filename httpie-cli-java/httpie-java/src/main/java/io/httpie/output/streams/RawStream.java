package io.httpie.output.streams;

import io.httpie.models.HTTPMessage;
import io.httpie.models.OutputOptions;

import java.util.Iterator;
import java.util.List;

public class RawStream extends Streams {
    public RawStream(HTTPMessage msg, OutputOptions outputOptions) {
        super(msg, outputOptions);
    }

    @Override
    protected Iterator<byte[]> iterBody() {
        return List.of(msg.getBody()).iterator();
    }
}
