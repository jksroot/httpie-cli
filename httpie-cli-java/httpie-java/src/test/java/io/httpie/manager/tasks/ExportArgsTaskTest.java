package io.httpie.manager.tasks;

public class ExportArgsTaskTest {
    public static void main(String[] args) {
        testExportJson();
        System.out.println("ExportArgsTaskTest passed!");
    }

    static void testExportJson() {
        String json = ExportArgsTask.exportAsJson();
        if (!json.startsWith("[")) throw new AssertionError("Expected JSON array");
    }
}
