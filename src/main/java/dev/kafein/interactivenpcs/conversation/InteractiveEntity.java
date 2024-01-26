package dev.kafein.interactivenpcs.conversation;

import org.jetbrains.annotations.Nullable;

public interface InteractiveEntity {
    int getId();

    String getName();

    DialogueMap getDialogueMap();

    @Nullable Focus getFocus();
}
