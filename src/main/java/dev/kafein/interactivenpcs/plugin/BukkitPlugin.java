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

import com.comphenix.protocol.ProtocolManager;
import dev.kafein.interactivenpcs.command.CommandManager;
import dev.kafein.interactivenpcs.configuration.ConfigManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

public interface BukkitPlugin {
    void load();

    void enable();

    void disable();

    void startTasks();

    void loadConfigs();

    void registerCommands();

    void registerListeners();

    Plugin getPlugin();

    Path getDataPath();

    Logger getLogger();

    ConfigManager getConfigManager();

    CommandManager getCommandManager();

    BukkitAudiences getBukkitAudiences();

    ProtocolManager getProtocolManager();

    default <T extends BukkitPlugin> T getAs(@NotNull Class<T> clazz) {
        return clazz.cast(this);
    }
}
