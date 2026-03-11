package io.httpie.uploads;

import java.io.ByteArrayInputStream;

public class UploadsTest {
    public static void main(String[] args) throws Exception {
        byte[] data = Uploads.prepareRequestBody(null, "hello");
        assert new String(data).equals("hello");
        byte[] read = Uploads.prepareRequestBody(null, new ByteArrayInputStream("abc".getBytes()));
        assert new String(read).equals("abc");
        System.out.println("UploadsTest passed");
    }
}
