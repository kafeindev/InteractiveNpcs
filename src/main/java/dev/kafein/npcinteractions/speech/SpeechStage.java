package dev.kafein.npcinteractions.speech;

import dev.kafein.npcinteractions.objective.Objective;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public final class SpeechStage {
    private final List<String> messages;
    private final double speed;
    private final @Nullable Objective objective;

    public SpeechStage(List<String> messages, double speed, @Nullable Objective objective) {
        this.messages = messages;
        this.speed = speed;
        this.objective = objective;
    }

    public List<String> getMessages() {
        return this.messages;
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
        return this.messages.equals(other.messages)
                && this.speed == other.speed
                && this.objective == other.objective;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.messages, this.speed, this.objective);
    }

    @Override
    public String toString() {
        return "SpeechStage{"
                + "messages=" + this.messages
                + ", speed=" + this.speed
                + ", objective=" + this.objective
                + "}";
    }
}
