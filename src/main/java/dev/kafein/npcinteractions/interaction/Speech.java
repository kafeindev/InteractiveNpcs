package dev.kafein.npcinteractions.interaction;

import dev.kafein.npcinteractions.objective.Objective;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Speech {
    private final Map<Integer, SpeechStage> stages;
    private final Set<Objective> objectives;

    public Speech(Map<Integer, SpeechStage> stages, Set<Objective> objectives) {
        this.stages = stages;
        this.objectives = objectives;
    }

    public Map<Integer, SpeechStage> getStages() {
        return this.stages;
    }

    public SpeechStage getStage(int stage) {
        return this.stages.get(stage);
    }

    public SpeechStage getFirstStage() {
        return this.getStage(1);
    }

    public Set<Objective> getObjectives() {
        return this.objectives;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Speech)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Speech other = (Speech) obj;
        return this.stages.equals(other.stages) && this.objectives.equals(other.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stages, this.objectives);
    }

    @Override
    public String toString() {
        return "Speech{" +
                "stages=" + this.stages +
                ", objectives=" + this.objectives +
                '}';
    }
}
