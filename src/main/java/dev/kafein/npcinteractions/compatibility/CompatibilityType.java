package dev.kafein.npcinteractions.compatibility;

public enum CompatibilityType {
    VAULT("Vault"),
    CITIZENS("Citizens");

    private final String pluginName;

    CompatibilityType(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginName() {
        return this.pluginName;
    }
}
