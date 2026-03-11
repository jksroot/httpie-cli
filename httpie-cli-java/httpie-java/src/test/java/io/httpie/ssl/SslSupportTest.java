package io.httpie.ssl;

public class SslSupportTest {
    public static void main(String[] args) {
        String ciphers = SslSupport.defaultCipherString();
        assert ciphers.contains("TLS_AES");
        System.out.println("SslSupportTest passed");
    }
}
