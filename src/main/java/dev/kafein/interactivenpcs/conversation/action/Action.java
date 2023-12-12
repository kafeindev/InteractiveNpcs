package dev.kafein.interactivenpcs.conversation.action;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Action {
    void execute(@NotNull Player player, @NotNull String value);
}
