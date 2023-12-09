package dev.kafein.interactivenpcs.condition;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public interface Condition extends Predicate<Player> {
    void apply(Player player);

    @NotNull String getType();
}
