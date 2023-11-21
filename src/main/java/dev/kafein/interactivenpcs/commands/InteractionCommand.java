package dev.kafein.interactivenpcs.commands;

import com.google.common.collect.ImmutableList;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.command.AbstractCommand;
import dev.kafein.interactivenpcs.command.CommandProperties;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class InteractionCommand extends AbstractCommand {
    private final InteractiveNpcs plugin;

    public InteractionCommand(InteractiveNpcs plugin) {
        super(CommandProperties.newBuilder()
                        .name("interaction")
                        .usage("/interaction")
                        .description("Command for InteractiveNpcs plugin")
                        .permission("interactions.admin")
                        .build(),
                ImmutableList.of(
                    new ReloadCommand(plugin)
                ));
        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {

    }
}
