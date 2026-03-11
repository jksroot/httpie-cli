package io.httpie.output.streams;

import io.httpie.models.HTTPMessage;
import io.httpie.models.OutputOptions;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Streams implements Iterable<byte[]> {
    protected final HTTPMessage msg;
    protected final OutputOptions outputOptions;

    public Streams(HTTPMessage msg, OutputOptions outputOptions) {
        this.msg = msg;
        this.outputOptions = outputOptions;
    }

    public byte[] getHeaders() { return msg.getHeaders().getBytes(); }
    public byte[] getMetadata() { return "".getBytes(); }

    protected abstract Iterator<byte[]> iterBody();

    @Override
    public Iterator<byte[]> iterator() {
        return new Iterator<>() {
            private boolean headersDone = false;
            private boolean bodyDone = false;
            private Iterator<byte[]> bodyIter = iterBody();

            @Override
            public boolean hasNext() {
                if (!headersDone && outputOptions.headers) return true;
                if (!bodyDone && outputOptions.body) return bodyIter.hasNext();
                return false;
            }

            @Override
            public byte[] next() {
                if (!headersDone && outputOptions.headers) {
                    headersDone = true;
                    return getHeaders();
                }
                if (!bodyDone && outputOptions.body) {
                    if (bodyIter.hasNext()) {
                        return bodyIter.next();
                    } else {
                        bodyDone = true;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }
}
