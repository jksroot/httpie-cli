package io.httpie.manager;

import java.io.IOException;
import java.util.List;

public class ManagerCompat {

    public static class PipError extends RuntimeException {
        public final byte[] stdout;
        public final byte[] stderr;

        public PipError(byte[] stdout, byte[] stderr) {
            this.stdout = stdout;
            this.stderr = stderr;
        }
    }

    public static byte[] runPip(List<String> args) {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(args);
        try {
            Process process = pb.start();
            byte[] stdout = process.getInputStream().readAllBytes();
            byte[] stderr = process.getErrorStream().readAllBytes();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new PipError(stdout, stderr);
            }
            return stdout;
        } catch (IOException | InterruptedException e) {
            throw new PipError(new byte[0], e.getMessage().getBytes());
        }
    }
}
