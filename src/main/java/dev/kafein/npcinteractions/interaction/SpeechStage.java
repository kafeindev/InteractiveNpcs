package dev.kafein.npcinteractions.interaction;

import dev.kafein.npcinteractions.objective.Objective;

import java.util.Objects;
import java.util.Set;

public final class SpeechStage {
    private final int stage;
    private final Set<Objective> objectives;

    public SpeechStage(int stage, Set<Objective> objectives) {
        this.stage = stage;
        this.objectives = objectives;
    }

    public int getStage() {
        return this.stage;
    }

    public Set<Objective> getObjectives() {
        return this.objectives;
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
        return this.stage == other.stage && this.objectives.equals(other.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stage, this.objectives);
    }

    @Override
    public String toString() {
        return "SpeechStage{" +
                "stage=" + this.stage +
                ", objectives=" + this.objectives +
                '}';
    }
}
