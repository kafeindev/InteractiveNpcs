package dev.kafein.interactivenpcs.speech;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

public final class Speech {
    private final SpeechType type;
    private final Map<Integer, SpeechStage> stages;

    private final @Nullable String imageSymbol;
    private final int imageWidth;
    private final int linesOffset;

    public Speech(SpeechType type, Map<Integer, SpeechStage> stages, @Nullable String imageSymbol, int imageWidth, int linesOffset) {
        this.type = type;
        this.stages = stages;
        this.imageSymbol = imageSymbol;
        this.imageWidth = imageWidth;
        this.linesOffset = linesOffset;
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

    public @Nullable String getImageSymbol() {
        return this.imageSymbol;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public int getLinesOffset() {
        return this.linesOffset;
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
        return this.type == other.type
                && Objects.equals(this.stages, other.stages)
                && Objects.equals(this.imageSymbol, other.imageSymbol)
                && this.imageWidth == other.imageWidth
                && this.linesOffset == other.linesOffset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.stages, this.imageSymbol, this.imageWidth, this.linesOffset);
    }

    @Override
    public String toString() {
        return "Speech{" +
                "type=" + this.type +
                ", stages=" + this.stages +
                ", imageSymbol='" + this.imageSymbol + '\'' +
                ", imageWidth=" + this.imageWidth +
                ", linesOffset=" + this.linesOffset +
                '}';
    }
}
