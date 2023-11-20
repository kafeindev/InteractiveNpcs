package dev.kafein.npcinteractions.compatibility;

import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.compatibility.implementation.CitizensCompatibility;
import dev.kafein.npcinteractions.compatibility.implementation.VaultCompatibility;
import org.jetbrains.annotations.NotNull;

public final class CompatibilityFactory {
    private CompatibilityFactory() {
    }

    public static Compatibility createCompatibility(@NotNull CompatibilityType type, @NotNull NpcInteractions plugin) {
        switch (type) {
            case VAULT:
                return new VaultCompatibility(plugin);
            case CITIZENS:
                return new CitizensCompatibility(plugin);
            default:
                throw new IllegalArgumentException("Unknown compatibility type: " + type);
        }
    }
}
