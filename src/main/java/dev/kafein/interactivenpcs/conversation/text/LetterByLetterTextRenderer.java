package dev.kafein.interactivenpcs.conversation.text;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class LetterByLetterTextRenderer extends AbstractTextRenderer {
    public LetterByLetterTextRenderer(@NotNull List<String> lines) {
        super(lines);
    }

    public static LetterByLetterTextRenderer of(@NotNull List<String> lines) {
        return new LetterByLetterTextRenderer(lines);
    }

    @Override
    String renderLine(String line, String lineToWrite) {
        char character = lineToWrite.charAt(line.length());
        return line + character;
    }
}
