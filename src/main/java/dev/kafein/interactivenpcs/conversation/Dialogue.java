package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.conversation.text.TextLines;

import java.util.Objects;

public final class Dialogue {
    private final String id;
    private final TextLines textLines;
    private final AnswerMap answerMap;

    public Dialogue(String id, TextLines textLines) {
        this(id, textLines, new AnswerMap());
    }

    public Dialogue(String id, TextLines textLines, AnswerMap answerMap) {
        this.id = id;
        this.textLines = textLines;
        this.answerMap = answerMap;
    }

    public static Dialogue of(String id, TextLines textLines) {
        return new Dialogue(id, textLines);
    }

    public static Dialogue of(String id, TextLines textLines, AnswerMap answerMap) {
        return new Dialogue(id, textLines, answerMap);
    }

    public String getId() {
        return this.id;
    }

    public TextLines getTextLines() {
        return this.textLines;
    }

    public AnswerMap getAnswerMap() {
        return this.answerMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dialogue)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Dialogue other = (Dialogue) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.textLines, other.textLines)
                && Objects.equals(this.answerMap, other.answerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.textLines, this.answerMap);
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + this.id + '\'' +
                ", textLines=" + this.textLines +
                ", answerMap=" + this.answerMap +
                '}';
    }
}
