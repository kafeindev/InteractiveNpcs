package dev.kafein.npcinteractions.interaction;

import java.util.Objects;

public final class InteractionStage {
    private final int stage;
    private final Speech speech;

    public InteractionStage(int stage, Speech speech) {
        this.stage = stage;
        this.speech = speech;
    }

    public int getStage() {
        return this.stage;
    }

    public Speech getSpeech() {
        return this.speech;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InteractionStage)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        InteractionStage other = (InteractionStage) obj;
        return this.stage == other.stage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stage, this.speech);
    }

    @Override
    public String toString() {
        return "InteractionStage{" +
                "stage=" + this.stage +
                ", speech=" + this.speech +
                "}";
    }
}
