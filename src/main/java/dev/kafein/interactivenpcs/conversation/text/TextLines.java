package dev.kafein.interactivenpcs.conversation.text;

import java.util.*;

public final class TextLines {
    private final List<String> lines;
    private final Map<Locale, List<String>> localizedLines;

    public TextLines(List<String> lines, Map<Locale, List<String>> localizedLines) {
        this.lines = lines;
        this.localizedLines = localizedLines;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public Map<Locale, List<String>> getLocalizedLines() {
        return this.localizedLines;
    }

    public List<String> getLocalizedLines(Locale locale) {
        return Optional.ofNullable(this.localizedLines.get(locale)).orElse(this.lines);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TextLines)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        TextLines other = (TextLines) obj;
        return Objects.equals(this.lines, other.lines) && Objects.equals(this.localizedLines, other.localizedLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lines, this.localizedLines);
    }

    @Override
    public String toString() {
        return "TextLines{" +
                "lines=" + this.lines +
                ", localizedLines=" + this.localizedLines +
                '}';
    }
}
