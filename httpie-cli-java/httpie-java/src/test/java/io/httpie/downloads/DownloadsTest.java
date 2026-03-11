package io.httpie.downloads;

public class DownloadsTest {
    public static void main(String[] args) {
        String header = "bytes 0-9/10";
        int total = Downloads.parseContentRange(header, 0);
        assert total == 10;
        String name = Downloads.filenameFromUrl("http://example.com/test");
        assert name.equals("test");
        System.out.println("DownloadsTest passed");
    }
}
