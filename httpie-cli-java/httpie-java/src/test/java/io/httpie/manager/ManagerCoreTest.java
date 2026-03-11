package io.httpie.manager;

public class ManagerCoreTest {
    public static void main(String[] args) {
        testMsgNakedInvocation();
        System.out.println("ManagerCoreTest passed!");
    }

    static void testMsgNakedInvocation() {
        String msg = ManagerCore.msgNakedInvocation();
        if (!msg.contains("http")) throw new AssertionError("Expected http hint");
    }
}
