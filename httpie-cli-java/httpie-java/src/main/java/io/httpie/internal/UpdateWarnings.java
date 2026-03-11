package io.httpie.internal;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class UpdateWarnings {
    public static final String PACKAGE_INDEX_LINK = "https://packages.httpie.io/latest.json";
    public static final Duration FETCH_INTERVAL = Duration.ofDays(14);
    public static final Duration WARN_INTERVAL = Duration.ofDays(7);

    public static final String UPDATE_MESSAGE_FORMAT = 
        "A new HTTPie release (%s) is available.\n" +
        "To see how you can update, please visit https://httpie.io/docs/cli/%s\n";

    public static final String ALREADY_UP_TO_DATE_MESSAGE = "You are already up-to-date.\n";

    public static String getUpdateMessage(String lastReleasedVersion, String installationMethod) {
        return String.format(UPDATE_MESSAGE_FORMAT, lastReleasedVersion, installationMethod);
    }

    public static boolean shouldFetchUpdates(Instant lastFetchedDate) {
        if (lastFetchedDate == null) return true;
        return Instant.now().isAfter(lastFetchedDate.plus(FETCH_INTERVAL));
    }

    public static boolean shouldWarn(Instant lastWarnedDate) {
        if (lastWarnedDate == null) return true;
        return Instant.now().isAfter(lastWarnedDate.plus(WARN_INTERVAL));
    }
}
