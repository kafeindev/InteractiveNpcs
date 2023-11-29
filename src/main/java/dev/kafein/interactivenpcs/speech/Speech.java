package dev.kafein.interactivenpcs.speech;

import java.util.Map;
import java.util.Objects;

public final class Speech {
    private final SpeechType type;
    private final Map<Integer, SpeechStage> stages;

    public Speech(SpeechType type, Map<Integer, SpeechStage> stages) {
        this.type = type;
        this.stages = stages;
    }

    public SpeechType getType() {
        return this.type;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Speech)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Speech other = (Speech) obj;
        return this.type == other.type && this.stages.equals(other.stages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.stages);
    }

    @Override
    public String toString() {
        return "Speech{" +
                "type=" + this.type +
                ", stages=" + this.stages +
                "}";
    }
}
