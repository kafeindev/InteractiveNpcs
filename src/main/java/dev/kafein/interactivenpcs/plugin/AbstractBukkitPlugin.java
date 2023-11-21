/*
 * MIT License
 *
 * Copyright (c) 2023 Kafein
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.kafein.interactivenpcs.plugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.kafein.interactivenpcs.command.Command;
import dev.kafein.interactivenpcs.command.CommandManager;
import dev.kafein.interactivenpcs.configuration.ConfigManager;
import dev.kafein.interactivenpcs.listener.registrar.ListenerRegistrar;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;

import java.nio.file.Path;
import java.util.Set;
import java.util.logging.Logger;

public abstract class AbstractBukkitPlugin implements BukkitPlugin {
    private final Plugin plugin;

    private ConfigManager configManager;
    private CommandManager commandManager;
    private BukkitAudiences bukkitAudiences;
    private ProtocolManager protocolManager;

    protected AbstractBukkitPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        onLoad();
    }

    public abstract void onLoad();

    @Override
    public void enable() {
        getLogger().info("Loading configs...");
        this.configManager = new ConfigManager(getDataPath());
        loadConfigs();

        onEnable();

        this.bukkitAudiences = BukkitAudiences.create(this.plugin);

        getLogger().info("Starting tasks...");
        startTasks();

        getLogger().info("Registering commands...");
        this.commandManager = new CommandManager(this.plugin);
        registerCommands();

        getLogger().info("Registering listeners...");
        registerListeners();

        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public abstract void onEnable();

    @Override
    public void disable() {
        onDisable();

        this.bukkitAudiences.close();
    }

    public abstract void onDisable();

    @Override
    public void registerCommands() {
        getCommands().forEach(command -> getCommandManager().registerCommand(command));
    }

    public abstract Set<Command> getCommands();

    @Override
    public void registerListeners() {
        ListenerRegistrar.register(this, getListeners());
    }

    public abstract Set<Class<?>> getListeners();

    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Path getDataPath() {
        return this.plugin.getDataFolder().toPath().toAbsolutePath();
    }

    @Override
    public Logger getLogger() {
        return this.plugin.getLogger();
    }

    @Override
    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public BukkitAudiences getBukkitAudiences() {
        return this.bukkitAudiences;
    }

    @Override
    public ProtocolManager getProtocolManager() {
        return this.protocolManager;
    }
}
