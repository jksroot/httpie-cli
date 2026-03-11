package io.httpie.cookies;

public class HTTPieCookiePolicy {
    private static final String LOCALHOST = "localhost";
    private static final String LOCALHOST_SUFFIX = ".localhost";

    public boolean isLocalhost(String hostname) {
        return LOCALHOST.equals(hostname) || (hostname != null && hostname.endsWith(LOCALHOST_SUFFIX));
    }
}
