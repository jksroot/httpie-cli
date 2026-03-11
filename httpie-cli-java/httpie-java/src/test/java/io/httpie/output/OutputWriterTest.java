package io.httpie.output;

import io.httpie.models.HTTPMessage;
import io.httpie.models.OutputOptions;

public class OutputWriterTest {
    static class DummyMessage extends HTTPMessage {
        @Override public String getHeaders() { return "Header: Value"; }
        @Override public byte[] getBody() { return "body".getBytes(); }
        @Override public String getContentType() { return "text/plain"; }
    }

    public static void main(String[] args) throws Exception {
        testWriteMessage();
        System.out.println("OutputWriterTest passed!");
    }

    static void testWriteMessage() throws Exception {
        DummyMessage msg = new DummyMessage();
        byte[] data = OutputWriter.writeMessage(msg, new OutputOptions(true, true, false));
        String output = new String(data);
        if (!output.contains("Header")) throw new AssertionError("Expected header");
    }
}
