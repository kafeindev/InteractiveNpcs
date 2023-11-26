package dev.kafein.interactivenpcs.utils;

import org.bukkit.Bukkit;

public final class ProtocolVersion {
    private static int VERSION = 0;

    private ProtocolVersion() {}

    public static int getVersion() {
        if (VERSION == 0) {
            String bukkitVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
            VERSION = Integer.parseInt(bukkitVersion.substring(3, 5));
        }

        return VERSION;
    }
}
