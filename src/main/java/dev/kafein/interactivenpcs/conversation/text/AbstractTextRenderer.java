package dev.kafein.interactivenpcs.conversation.text;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextRenderer implements TextRenderer {
    private final List<String> lines;
    private final List<String> renderedLines;

    private boolean hasNext = true;

    public AbstractTextRenderer(List<String> lines) {
        this.lines = lines;
        this.renderedLines = new ArrayList<>();
    }

    @Override
    public List<String> render() {
        if (!this.hasNext || this.renderedLines.equals(this.lines)) {
            this.hasNext = false;
            return this.renderedLines;
        }

        if (this.renderedLines.isEmpty()) {
            this.renderedLines.add("");
        }

        String line = this.renderedLines.get(this.renderedLines.size() - 1);
        String lineToWrite = this.lines.get(this.renderedLines.size() - 1);

        if (line.length() >= lineToWrite.length()) {
            this.renderedLines.add("");
            return this.renderedLines;
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
        this.renderedLines.set(this.renderedLines.size() - 1, line);

        return this.renderedLines;
    }

    abstract String renderLine(String line, String lineToWrite);

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }
}
