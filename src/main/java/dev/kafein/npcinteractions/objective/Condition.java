package dev.kafein.npcinteractions.objective;

import org.bukkit.entity.Player;

import java.util.function.Predicate;

@FunctionalInterface
public interface Condition extends Predicate<Player> {
}
