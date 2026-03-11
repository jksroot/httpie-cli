package io.httpie.core;

import io.httpie.cli.HttpieCli;
import io.httpie.config.Config;
import io.httpie.context.Environment;
import io.httpie.status.ExitStatus;

public class Core {
    public static void main(String[] args) {
        Environment env = new Environment(Config.getDefaultConfigDir());
        ExitStatus status = main(args, env);
        System.exit(status.getCode());
    }

    public static ExitStatus main(String[] args, Environment env) {
        try {
            HttpieCli cli = new HttpieCli();
            if (cli.parse(args)) {
                cli.run();
                return ExitStatus.SUCCESS;
            }
            return ExitStatus.ERROR;
        } catch (Exception e) {
            env.logError(e.getMessage(), Environment.LogLevel.ERROR);
            return ExitStatus.ERROR;
        }
    }
}
