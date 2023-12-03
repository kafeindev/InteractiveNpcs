package dev.kafein.interactivenpcs.speech.condition;

import org.bukkit.entity.Player;

import java.util.function.Predicate;

public interface Condition extends Predicate<Player> {
    void apply(Player player);
}
