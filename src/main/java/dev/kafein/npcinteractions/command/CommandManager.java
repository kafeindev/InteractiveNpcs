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

package dev.kafein.npcinteractions.command;

import dev.kafein.npcinteractions.command.context.CommandContext;
import dev.kafein.npcinteractions.command.context.CommandContextMap;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CommandManager {
    private final CommandRegistrar commandRegistrar;
    private final CommandContextMap commandContextMap;

    public CommandManager(Plugin plugin) {
        this.commandRegistrar = new CommandRegistrar(plugin);
        this.commandContextMap = new CommandContextMap();
    }

    public void registerCommand(@NotNull Command command) {
        this.commandRegistrar.registerCommand(command);
    }

    public CommandContextMap getCommandContextMap() {
        return this.commandContextMap;
    }

    public <T> @Nullable CommandContext<T> getCommandContext(@NotNull Class<T> clazz) {
        return this.commandContextMap.getCommandContext(clazz);
    }

    public <T> void registerCommandContext(@NotNull Class<T> clazz, @NotNull CommandContext<T> commandContext) {
        this.commandContextMap.register(clazz, commandContext);
    }
}
