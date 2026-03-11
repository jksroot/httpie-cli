package io.httpie.manager;

public class ManagerCore {
    public static final String MSG_COMMAND_CONFUSION = "This command is only for managing HTTPie plugins.\n" +
            "To send a request, please use the http/https commands:\n\n" +
            "  $ http %s\n\n" +
            "  $ https %s\n";

    public static String msgNakedInvocation() {
        return ManagerCli.missingSubcommand() + "\n\n" +
                String.format(MSG_COMMAND_CONFUSION, "POST pie.dev/post hello=world", "POST pie.dev/post hello=world");
    }
}
