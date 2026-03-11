package io.httpie.cookies;

public class HTTPieCookiePolicyTest {
    public static void main(String[] args) {
        HTTPieCookiePolicy policy = new HTTPieCookiePolicy();
        assert policy.isLocalhost("localhost");
        assert policy.isLocalhost("api.localhost");
        assert !policy.isLocalhost("example.com");
        System.out.println("HTTPieCookiePolicyTest passed");
    }
}
