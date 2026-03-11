package io.httpie.manager;

public class ManagerMain {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println(ManagerCore.msgNakedInvocation());
            System.exit(1);
        }
        // Manager functionality would be here.
        System.out.println("Manager command invoked: " + String.join(" ", args));
    }
}
