package dev.kafein.interactivenpcs.compatibility;

import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.compatibility.implementation.CitizensCompatibility;
import dev.kafein.interactivenpcs.compatibility.implementation.VaultCompatibility;
import org.jetbrains.annotations.NotNull;

public final class CompatibilityFactory {
    private CompatibilityFactory() {
    }

    public static Compatibility createCompatibility(@NotNull CompatibilityType type, @NotNull InteractiveNpcs plugin) {
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
