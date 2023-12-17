package dev.kafein.interactivenpcs.conversation.text;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class WordByWordTextRenderer extends AbstractTextRenderer {
    public WordByWordTextRenderer(@NotNull List<String> lines) {
        super(lines);
    }

    public static WordByWordTextRenderer of(@NotNull List<String> lines) {
        return new WordByWordTextRenderer(lines);
    }

    @Override
    String renderLine(String line, String lineToWrite) {
        String[] words = lineToWrite.split(" ");
        String[] wordsInLine = line.split(" ");
        if (wordsInLine.length >= words.length) {
            return line;
        }

        return line + " " + words[wordsInLine.length];
    }
}
