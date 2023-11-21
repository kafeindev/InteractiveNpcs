package dev.kafein.interactivenpcs;

import com.google.common.collect.ImmutableSet;
import dev.kafein.interactivenpcs.command.Command;
import dev.kafein.interactivenpcs.commands.InteractionCommand;
import dev.kafein.interactivenpcs.compatibility.Compatibility;
import dev.kafein.interactivenpcs.compatibility.CompatibilityFactory;
import dev.kafein.interactivenpcs.compatibility.CompatibilityType;
import dev.kafein.interactivenpcs.interaction.InteractionManager;
import dev.kafein.interactivenpcs.plugin.AbstractBukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Set;

public final class InteractiveNpcs extends AbstractBukkitPlugin {
    private InteractionManager interactionManager;

    public InteractiveNpcs(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void onLoad() {}

    @Override
    public void onEnable() {
        this.interactionManager = new InteractionManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        for (CompatibilityType compatibilityType : CompatibilityType.values()) {
            if (pluginManager.isPluginEnabled(compatibilityType.getPluginName())) {
                continue;
            }

            Compatibility compatibility = CompatibilityFactory.createCompatibility(compatibilityType, this);
            compatibility.initialize();
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public Set<Command> getCommands() {
        return ImmutableSet.of(
                new InteractionCommand(this)
        );
    }

    @Override
    public Set<Class<?>> getListeners() {
        return null;
    }

    @Override
    public void startTasks() {

    }

    @Override
    public void loadConfigs() {

    }

    public InteractionManager getInteractionManager() {
        return this.interactionManager;
    }
}
