package io.httpie.output.ui;

public class RichProgress {
    public static String progressMessage(int percent) {
        return "Progress: " + percent + "%";
    }
}
