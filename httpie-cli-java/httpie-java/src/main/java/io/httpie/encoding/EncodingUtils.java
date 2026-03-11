package io.httpie.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingUtils {
    public static final String UTF8 = "utf-8";

    public static String detectEncoding(byte[] content) {
        if (content == null || content.length == 0) {
            return UTF8;
        }
        return UTF8; // Simplified: default to UTF-8.
    }

    public static Decoded smartDecode(byte[] content, String encoding) {
        if (encoding == null || encoding.isBlank()) {
            encoding = detectEncoding(content);
        }
        String decoded = new String(content, Charset.forName(encoding));
        return new Decoded(decoded, encoding);
    }

    public static byte[] smartEncode(String content, String encoding) {
        if (encoding == null || encoding.isBlank()) {
            encoding = UTF8;
        }
        return content.getBytes(Charset.forName(encoding));
    }

    public record Decoded(String content, String encoding) {}
}
