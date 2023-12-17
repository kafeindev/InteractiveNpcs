package dev.kafein.interactivenpcs.conversation.text;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TextRenderer {
    void render(@NotNull List<String> input);

    static TextRenderer letterByLetter(@NotNull TextLines textLines) {
        return LetterByLetterTextRenderer.of(textLines.getLines());
    }

    static TextRenderer letterByLetter(@NotNull List<String> lines) {
        return LetterByLetterTextRenderer.of(lines);
    }

    static TextRenderer wordByWord(@NotNull TextLines textLines) {
        return WordByWordTextRenderer.of(textLines.getLines());
    }

    static TextRenderer wordByWord(@NotNull List<String> lines) {
        return WordByWordTextRenderer.of(lines);
    }
}
