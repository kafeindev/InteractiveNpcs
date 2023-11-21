package dev.kafein.interactivenpcs.compatibility.implementation;

import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.compatibility.Compatibility;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

public final class VaultCompatibility implements Compatibility {
    private static @Nullable Economy economy;

    private final InteractiveNpcs plugin;

    public VaultCompatibility(InteractiveNpcs plugin) {
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
