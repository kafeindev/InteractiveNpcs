package dev.kafein.interactivenpcs.commands;

import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.command.AbstractCommand;
import dev.kafein.interactivenpcs.command.CommandProperties;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

final class ReloadCommand extends AbstractCommand {
    private final InteractiveNpcs plugin;

    ReloadCommand(InteractiveNpcs plugin) {
        super(CommandProperties.newBuilder()
                .name("reload")
                .build());
        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        //update interactions


    }
}
