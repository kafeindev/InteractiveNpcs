package dev.kafein.interactivenpcs.conversation;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class ConversationOptions {
    private final ConversationType type;
    private final @Nullable String imageSymbol;
    private final int imageWidth;
    private final int linesOffset;

    public ConversationOptions(ConversationType type, @Nullable String imageSymbol, int imageWidth, int linesOffset) {
        this.type = type;
        this.imageSymbol = imageSymbol;
        this.imageWidth = imageWidth;
        this.linesOffset = linesOffset;
    }

    public ConversationType getType() {
        return this.type;
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
        if (!(obj instanceof ConversationOptions)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        ConversationOptions other = (ConversationOptions) obj;
        return this.type == other.type
                && Objects.equals(this.imageSymbol, other.imageSymbol)
                && this.imageWidth == other.imageWidth
                && this.linesOffset == other.linesOffset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.imageSymbol, this.imageWidth, this.linesOffset);
    }

    @Override
    public String toString() {
        return "ConversationOptions{" +
                "type=" + this.type +
                ", imageSymbol='" + this.imageSymbol + '\'' +
                ", imageWidth=" + this.imageWidth +
                ", linesOffset=" + this.linesOffset +
                '}';
    }
}
