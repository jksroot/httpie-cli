package io.httpie.internal;

import java.time.Instant;

public class UpdateWarningsTest {
    public static void main(String[] args) {
        testShouldFetchUpdates();
        testShouldWarn();
        System.out.println("UpdateWarningsTest passed!");
    }

    static void testShouldFetchUpdates() {
        if (!UpdateWarnings.shouldFetchUpdates(null)) throw new AssertionError("Expected true for null");
        if (UpdateWarnings.shouldFetchUpdates(Instant.now())) throw new AssertionError("Expected false for now");
        if (!UpdateWarnings.shouldFetchUpdates(Instant.now().minus(UpdateWarnings.FETCH_INTERVAL.plusDays(1)))) 
            throw new AssertionError("Expected true for old date");
    }

    static void testShouldWarn() {
        if (!UpdateWarnings.shouldWarn(null)) throw new AssertionError("Expected true for null");
        if (UpdateWarnings.shouldWarn(Instant.now())) throw new AssertionError("Expected false for now");
        if (!UpdateWarnings.shouldWarn(Instant.now().minus(UpdateWarnings.WARN_INTERVAL.plusDays(1)))) 
            throw new AssertionError("Expected true for old date");
    }
}
