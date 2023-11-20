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

import com.google.common.collect.ImmutableList;
import dev.kafein.npcinteractions.command.completion.RegisteredTabCompletion;
import dev.kafein.npcinteractions.command.completion.TabCompletion;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

final class CommandAdapter extends BukkitCommand {
    private final Command command;

    CommandAdapter(Command command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.command = command;
    }

    @Override
    public boolean execute(@NotNull org.bukkit.command.CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!getAliases().contains(commandLabel)) {
            return false;
        }

        if (this.command.getPermission() != null && !sender.hasPermission(this.command.getPermission())) {
            //TODO: Send message
            return true;
        }

        if (args.length == 0) {
            this.command.execute(sender, args);
        } else {
            Command subCommand = this.command.findSubCommand(args);
            if (subCommand.getPermission() != null && !sender.hasPermission(subCommand.getPermission())) {
                //TODO: Send message
                return true;
            }

            String[] subCommandArgs = Arrays.copyOfRange(args, this.command.findSubCommandIndex(args), args.length);
            subCommand.execute(sender, subCommandArgs);
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull org.bukkit.command.CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (this.command.getPermission() != null && !sender.hasPermission(this.command.getPermission())) {
            return ImmutableList.of();
        }

        if (args.length == 0) {
            return complete(this.command, sender, 0);
        } else {
            Command subCommand = this.command.findSubCommand(args);
            if (subCommand.getPermission() != null && !sender.hasPermission(subCommand.getPermission())) {
                return ImmutableList.of();
            }

            int subCommandIndex = this.command.findSubCommandIndex(args);
            return complete(subCommand, sender, (args.length - subCommandIndex) - 1);
        }
    }

    private List<String> complete(@NotNull Command command, @NotNull CommandSender commandSender, int index) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();

        for (Command subCommand : command.getSubCommands()) {
            builder.addAll(subCommand.getAliases());
        }
        for (RegisteredTabCompletion registeredTabCompletion : command.getTabCompletions(index)) {
            TabCompletion tabCompletion = registeredTabCompletion.getTabCompletion();
            builder.addAll(tabCompletion.apply(commandSender));
        }

        return builder.build();
    }
}
