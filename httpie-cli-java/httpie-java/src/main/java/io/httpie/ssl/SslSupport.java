package io.httpie.ssl;

import java.util.List;

public class SslSupport {
    public static String defaultCipherString() {
        return String.join(":", List.of("TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384"));
    }
}
