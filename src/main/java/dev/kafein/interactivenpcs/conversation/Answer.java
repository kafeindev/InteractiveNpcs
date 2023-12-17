package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.condition.Condition;
import dev.kafein.interactivenpcs.conversation.text.TextLines;

import java.util.Objects;
import java.util.Set;

public final class Answer {
    private final String id;
    private final TextLines textLines;
    private final Set<Condition> conditions;

    public Answer(String id, TextLines textLines, Set<Condition> conditions) {
        this.id = id;
        this.textLines = textLines;
        this.conditions = conditions;
    }

    public String getId() {
        return this.id;
    }

    public TextLines getTextLines() {
        return this.textLines;
    }

    public Set<Condition> getConditions() {
        return this.conditions;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Answer)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Answer other = (Answer) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.textLines, other.textLines)
                && Objects.equals(this.conditions, other.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.textLines, this.conditions);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + this.id + '\'' +
                ", textLines=" + this.textLines +
                ", conditions=" + this.conditions +
                '}';
    }
}
