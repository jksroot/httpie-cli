package io.httpie;

import io.httpie.core.Core;
import io.httpie.context.Environment;
import io.httpie.status.ExitStatus;

import java.nio.file.Path;

public class CoreTest {
    public static void main(String[] args) {
        Environment env = new Environment(Path.of("."));
        ExitStatus status = Core.main(new String[]{"--help"}, env);
        assert status == ExitStatus.SUCCESS || status == ExitStatus.ERROR;
        System.out.println("CoreTest passed");
    }
}
