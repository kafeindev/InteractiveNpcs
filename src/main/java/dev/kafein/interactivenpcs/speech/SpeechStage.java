package dev.kafein.interactivenpcs.speech;

import dev.kafein.interactivenpcs.objective.Objective;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public final class SpeechStage {
    private final List<String> lines;
    private final double speed;
    private final @Nullable Objective objective;

    public SpeechStage(List<String> lines, double speed, @Nullable Objective objective) {
        this.lines = lines;
        this.speed = speed;
        this.objective = objective;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public double getSpeed() {
        return this.speed;
    }

    public @Nullable Objective getObjective() {
        return this.objective;
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
                && this.speed == other.speed
                && Objects.equals(this.objective, other.objective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lines, this.speed, this.objective);
    }

    @Override
    public String toString() {
        return "SpeechStage{"
                + "lines=" + this.lines
                + ", speed=" + this.speed
                + ", objective=" + this.objective
                + "}";
    }
}
