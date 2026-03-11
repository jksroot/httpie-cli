package io.httpie.compat;

import javax.net.ssl.SSLContext;

public class Compat {
    public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");
    public static final boolean IS_FROZEN = false;

    public static void ensureDefaultCertsLoaded(SSLContext sslContext) {
        // Java loads default certs automatically.
    }
}
