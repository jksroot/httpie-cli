package io.httpie.encoding;

public class EncodingUtilsTest {
    public static void main(String[] args) {
        byte[] data = "hello".getBytes();
        EncodingUtils.Decoded decoded = EncodingUtils.smartDecode(data, null);
        assert decoded.content().equals("hello");
        System.out.println("EncodingUtilsTest passed");
    }
}
