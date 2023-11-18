package dev.kafein.npcinteractions.commands;

import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.command.AbstractCommand;
import dev.kafein.npcinteractions.command.CommandProperties;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

final class ReloadCommand extends AbstractCommand {
    private final NpcInteractions plugin;

    ReloadCommand(NpcInteractions plugin) {
        super(CommandProperties.newBuilder()
                .name("reload")
                .build());
        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {

    }
}
