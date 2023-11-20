package dev.kafein.npcinteractions.compatibility.implementation;

import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.compatibility.Compatibility;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

public final class VaultCompatibility implements Compatibility {
    private static @Nullable Economy economy;

    private final NpcInteractions plugin;

    public VaultCompatibility(NpcInteractions plugin) {
        this.plugin = plugin;
    }

    @Override
    public void initialize() {
        RegisteredServiceProvider<Economy> serviceProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (serviceProvider == null) {
            this.plugin.getLogger().warning("Vault is not installed. Economy features will not be available.");
            return;
        }

        economy = serviceProvider.getProvider();
    }

    public static @Nullable Economy getEconomy() {
        return economy;
    }
}
