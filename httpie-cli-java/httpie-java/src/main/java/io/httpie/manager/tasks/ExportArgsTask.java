package io.httpie.manager.tasks;

import io.httpie.cli.Definition;

public class ExportArgsTask {
    public static String exportAsJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < Definition.OPTIONS.size(); i++) {
            Definition.Option opt = Definition.OPTIONS.get(i);
            sb.append("{\"name\":\"").append(opt.name).append("\",\"hasValue\":").append(opt.hasValue).append("}");
            if (i < Definition.OPTIONS.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
