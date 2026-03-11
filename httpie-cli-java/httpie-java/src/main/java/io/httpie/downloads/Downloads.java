package io.httpie.downloads;

import io.httpie.context.Environment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloads {
    public static final int PARTIAL_CONTENT = 206;

    public static class ContentRangeError extends RuntimeException {
        public ContentRangeError(String message) {
            super(message);
        }
    }

    private static final Pattern RANGE_PATTERN = Pattern.compile("^bytes (?<first>\\d+)-(?<last>\\d+)/(\\*|(?<len>\\d+))$");

    public static int parseContentRange(String contentRange, int resumedFrom) {
        if (contentRange == null) {
            throw new ContentRangeError("Missing Content-Range");
        }
        Matcher matcher = RANGE_PATTERN.matcher(contentRange);
        if (!matcher.matches()) {
            throw new ContentRangeError("Invalid Content-Range format " + contentRange);
        }
        int first = Integer.parseInt(matcher.group("first"));
        int last = Integer.parseInt(matcher.group("last"));
        String lenRaw = matcher.group("len");
        Integer len = lenRaw == null ? null : Integer.parseInt(lenRaw);
        if (first > last || (len != null && len <= last)) {
            throw new ContentRangeError("Invalid Content-Range returned: " + contentRange);
        }
        if (first != resumedFrom || (len != null && last + 1 != len)) {
            throw new ContentRangeError("Unexpected Content-Range returned (" + contentRange + ") for the requested Range (" + resumedFrom + ")");
        }
        return last + 1;
    }

    public static String filenameFromUrl(String url) {
        int idx = url.lastIndexOf('/');
        String name = idx == -1 ? "index" : url.substring(idx + 1);
        return name.isEmpty() ? "index" : name;
    }

    public static String trimFilename(String filename, int maxLen) {
        if (filename.length() <= maxLen) {
            return filename;
        }
        return filename.substring(0, Math.max(0, maxLen));
    }

    public static String getUniqueFilename(String filename) {
        if (!Files.exists(Path.of(filename))) {
            return filename;
        }
        int attempt = 1;
        while (true) {
            String candidate = filename + "-" + attempt;
            if (!Files.exists(Path.of(candidate))) {
                return candidate;
            }
            attempt++;
        }
    }

    public static class DownloadStatus {
        public long downloaded = 0;
        public Long totalSize = null;
        public Instant startedAt = null;

        public void started(Long totalSize) {
            this.totalSize = totalSize;
            this.startedAt = Instant.now();
        }

        public void chunkDownloaded(int size) {
            downloaded += size;
        }
    }

    public static class Downloader {
        private final Environment env;
        private final DownloadStatus status = new DownloadStatus();

        public Downloader(Environment env) {
            this.env = env;
        }

        public DownloadStatus getStatus() {
            return status;
        }

        public void start(Long totalSize) {
            status.started(totalSize);
        }

        public void chunkDownloaded(byte[] chunk) {
            status.chunkDownloaded(chunk.length);
        }
    }
}
