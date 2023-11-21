/*
 * MIT License
 *
 * Copyright (c) 2022-2023 Kafein
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

package dev.kafein.interactivenpcs.listener.registrar;

import static com.google.common.base.Preconditions.*;

import dev.kafein.interactivenpcs.plugin.BukkitPlugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public final class ListenerRegistrar {
    private ListenerRegistrar() {}

    public static void register(@NotNull BukkitPlugin plugin, @NotNull Set<Class<?>> listenerClasses) {
        checkArgument(!listenerClasses.isEmpty(), "Listener list is empty");

        Plugin handle = JavaPlugin.getProvidingPlugin(ListenerRegistrar.class);
        PluginManager pluginManager = handle.getServer().getPluginManager();

        for (Class<?> listenerClass : listenerClasses) {
            Listener listener = cast(plugin, listenerClass);
            pluginManager.registerEvents(listener, handle);
        }
    }

    private static Listener cast(@NotNull BukkitPlugin plugin, @NotNull Class<?> clazz) {
        try {
            return (Listener) clazz
                    .getConstructor(BukkitPlugin.class)
                    .newInstance(plugin);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Failed to cast listener", e);
        }
    }
}
