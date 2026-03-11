package io.httpie.sessions;

public class SessionsTest {
    public static void main(String[] args) {
        assert Sessions.isAnonymousSession("/tmp/foo");
        assert Sessions.sessionHostnameToDirname("example.com:8080", "test").contains("example.com_8080");
        System.out.println("SessionsTest passed");
    }
}
