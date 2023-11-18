package dev.kafein.npcinteractions.compatibility;

public enum CompatibilityType {
    CITIZENS("Citizens"),
    ZNPC("ServersNPC");

    private final String pluginName;

    CompatibilityType(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginName() {
        return this.pluginName;
    }
}
