package dev.kafein.npcinteractions.compatibility;

import dev.kafein.npcinteractions.compatibility.implementation.CitizensCompatibility;
import dev.kafein.npcinteractions.compatibility.implementation.ZNpcsCompatibility;
import org.jetbrains.annotations.NotNull;

public final class CompatibilityFactory {
    private CompatibilityFactory() {
    }

    public static Compatibility createCompatibility(@NotNull CompatibilityType type) {
        switch (type) {
            case CITIZENS:
                return new CitizensCompatibility();
            case ZNPC:
                return new ZNpcsCompatibility();
            default:
                throw new IllegalArgumentException("Unknown compatibility type: " + type);
        }
    }
}
