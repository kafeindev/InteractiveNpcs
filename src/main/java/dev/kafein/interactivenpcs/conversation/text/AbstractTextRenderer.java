package dev.kafein.interactivenpcs.conversation.text;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractTextRenderer implements TextRenderer {
    private final List<String> lines;

    public AbstractTextRenderer(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public void render(@NotNull List<String> input) {
        if (input.isEmpty()) {
            input.add("");
        }

        String line = input.get(input.size() - 1);
        String lineToWrite = this.lines.get(input.size() - 1);

        if (line.length() >= lineToWrite.length()) {
            input.add("");
            return;
        }

        char character = lineToWrite.charAt(line.length());
        if (character == '<') {
            int index = lineToWrite.indexOf('>', line.length());
            if (index != -1) {
                line = line + lineToWrite.substring(line.length(), index + 1);
            }
        } else if (character == '&' || character == '§'){
            line = line + lineToWrite.substring(line.length(), line.length() + 2);
        }

        line = renderLine(line, lineToWrite);
        input.set(input.size() - 1, line);
    }

    abstract String renderLine(String line, String lineToWrite);
}
