package dev.kafein.npcinteractions.objective;

import dev.kafein.npcinteractions.objective.condition.Condition;

import java.util.Set;

public final class Objective {
    private final Set<Condition> conditions;
    private final Set<Reward> rewards;

    public Objective(Set<Condition> conditions, Set<Reward> rewards) {
        this.conditions = conditions;
        this.rewards = rewards;
    }
}
