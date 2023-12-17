package dev.kafein.interactivenpcs;

import com.google.common.collect.ImmutableSet;
import dev.kafein.interactivenpcs.command.Command;
import dev.kafein.interactivenpcs.commands.InteractionCommand;
import dev.kafein.interactivenpcs.compatibility.Compatibility;
import dev.kafein.interactivenpcs.compatibility.CompatibilityFactory;
import dev.kafein.interactivenpcs.compatibility.CompatibilityType;
import dev.kafein.interactivenpcs.configuration.Config;
import dev.kafein.interactivenpcs.configuration.ConfigVariables;
import dev.kafein.interactivenpcs.conversation.ConversationManager;
import dev.kafein.interactivenpcs.conversation.text.font.CharWidthMap;
import dev.kafein.interactivenpcs.plugin.AbstractBukkitPlugin;
import dev.kafein.interactivenpcs.tasks.MovementControlTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Set;

public final class InteractiveNpcs extends AbstractBukkitPlugin {
    private ConversationManager conversationManager;
    private CharWidthMap charWidthMap;

    public InteractiveNpcs(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void onLoad() {}

    @Override
    public void onEnable() {
        this.charWidthMap = new CharWidthMap(this);
        this.charWidthMap.initialize();

        this.conversationManager = new ConversationManager(this);
        this.conversationManager.initialize();

        PluginManager pluginManager = Bukkit.getPluginManager();
        for (CompatibilityType compatibilityType : CompatibilityType.values()) {
            if (!pluginManager.isPluginEnabled(compatibilityType.getPluginName())) {
                continue;
            }

            Compatibility compatibility = CompatibilityFactory.createCompatibility(compatibilityType, this);
            if (compatibility != null) {
                compatibility.initialize();
            }
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
        return ImmutableSet.of();
    }

    @Override
    public void startTasks() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(getPlugin(), new MovementControlTask(this), 0L, 10L);
    }

    @Override
    public void loadConfigs() {
        Config settingsConfig = getConfigManager().register("settings", getClass(), "/settings.yml", "settings.yml");
        getConfigManager().injectKeys(ConfigVariables.class, settingsConfig);
    }

    public ConversationManager getConversationManager() {
        return this.conversationManager;
    }

    public CharWidthMap getCharWidthMap() {
        return this.charWidthMap;
    }
}
