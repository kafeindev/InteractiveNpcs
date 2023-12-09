package dev.kafein.interactivenpcs.conversation;

import org.jetbrains.annotations.Nullable;

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
}
