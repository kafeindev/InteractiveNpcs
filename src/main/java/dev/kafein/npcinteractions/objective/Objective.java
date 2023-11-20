package dev.kafein.npcinteractions.objective;

import com.google.common.collect.Sets;
import dev.kafein.npcinteractions.objective.condition.Condition;

import java.util.Objects;
import java.util.Set;

public final class Objective {
    private final Set<Condition> conditions;
    private final Set<Reward> rewards;

    public Objective(Set<Condition> conditions) {
        this(conditions, Sets.newHashSet());
    }

    public Objective(Set<Condition> conditions, Set<Reward> rewards) {
        this.conditions = conditions;
        this.rewards = rewards;
    }

    public Set<Condition> getConditions() {
        return this.conditions;
    }

    public Set<Reward> getRewards() {
        return this.rewards;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Objective)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Objective other = (Objective) obj;
        return this.conditions.equals(other.conditions) && this.rewards.equals(other.rewards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.conditions, this.rewards);
    }

    @Override
    public String toString() {
        return "Objective{" +
                "conditions=" + this.conditions +
                ", rewards=" + this.rewards +
                '}';
    }
}
