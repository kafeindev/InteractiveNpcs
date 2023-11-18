package dev.kafein.npcinteractions.compatibility.implementation;

import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.compatibility.Compatibility;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public final class CitizensCompatibility implements Compatibility, Listener {
    @Override
    public void initialize(@NotNull NpcInteractions plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, plugin.getPlugin());
    }

    /*@EventHandler(priority = EventPriority.HIGHEST)
    public void onClick*/
}
