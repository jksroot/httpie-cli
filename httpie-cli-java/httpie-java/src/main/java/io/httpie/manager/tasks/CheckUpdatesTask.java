package io.httpie.manager.tasks;

import io.httpie.internal.UpdateWarnings;

public class CheckUpdatesTask {
    public static String run() {
        return UpdateWarnings.ALREADY_UP_TO_DATE_MESSAGE;
    }
}
