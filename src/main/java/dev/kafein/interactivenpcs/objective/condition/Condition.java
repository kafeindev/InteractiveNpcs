package dev.kafein.interactivenpcs.objective.condition;

import org.bukkit.entity.Player;

import java.util.function.Predicate;

public interface Condition extends Predicate<Player> {
    void apply(Player player);
}
