package dev.kafein.npcinteractions.compatibility;

import dev.kafein.npcinteractions.compatibility.implementation.CitizensCompatibility;
import org.jetbrains.annotations.NotNull;

public final class CompatibilityFactory {
    private CompatibilityFactory() {
    }

    public static Compatibility createCompatibility(@NotNull CompatibilityType type) {
        switch (type) {
            case CITIZENS:
                return new CitizensCompatibility();
            default:
                throw new IllegalArgumentException("Unknown compatibility type: " + type);
        }
    }
}
