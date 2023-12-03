package dev.kafein.interactivenpcs.speech;

import dev.kafein.interactivenpcs.speech.condition.Condition;
import dev.kafein.interactivenpcs.speech.reward.Reward;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class SpeechStage {
    private final List<String> lines;
    private final @Nullable Set<Condition> conditions;
    private final @Nullable Set<Reward> rewards;

    private final double slowness;

    public SpeechStage(List<String> lines, double slowness) {
        this(lines, slowness, null, null);
    }

    public SpeechStage(List<String> lines, double slowness, @Nullable Set<Condition> conditions, @Nullable Set<Reward> rewards) {
        this.lines = lines;
        this.slowness = slowness;
        this.conditions = conditions;
        this.rewards = rewards;
    }

    public boolean testConditions(@NotNull Player player) {
        if (this.conditions == null) {
            return true;
        }
        return this.conditions.stream().allMatch(condition -> condition.test(player));
    }

    public void applyConditions(@NotNull Player player) {
        if (this.conditions == null) {
            return;
        }
        this.conditions.forEach(condition -> condition.apply(player));
    }

    public void applyRewards(@NotNull Player player) {
        if (this.rewards == null) {
            return;
        }
        this.rewards.forEach(reward -> reward.apply(player));
    }

    public List<String> getLines() {
        return this.lines;
    }

    public double getSlowness() {
        return this.slowness;
    }

    public @Nullable Set<Condition> getConditions() {
        return this.conditions;
    }

    public @Nullable Set<Reward> getRewards() {
        return this.rewards;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SpeechStage)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        SpeechStage other = (SpeechStage) obj;
        return Objects.equals(this.lines, other.lines)
                && this.slowness == other.slowness
                && Objects.equals(this.conditions, other.conditions)
                && Objects.equals(this.rewards, other.rewards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lines, this.slowness, this.conditions, this.rewards);
    }

    @Override
    public String toString() {
        return "SpeechStage{"
                + "lines=" + this.lines
                + ", slowness=" + this.slowness
                + ", conditions=" + this.conditions
                + ", rewards=" + this.rewards
                + "}";
    }
}
