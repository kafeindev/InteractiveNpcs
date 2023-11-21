package dev.kafein.npcinteractions.objective;

import com.google.common.collect.Sets;
import dev.kafein.npcinteractions.objective.condition.Condition;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

    public boolean testConditions(@NotNull Player player) {
        return this.conditions.stream().allMatch(condition -> condition.test(player));
    }

    public void applyConditions(@NotNull Player player) {
        this.conditions.forEach(condition -> condition.apply(player));
    }

    public Set<Condition> getConditions() {
        return this.conditions;
    }

    public void applyRewards(@NotNull Player player) {
        this.rewards.forEach(reward -> reward.apply(player));
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
