package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.condition.Condition;
import dev.kafein.interactivenpcs.conversation.text.TextList;

import java.util.Objects;
import java.util.Set;

public final class Answer {
    private final String id;
    private final TextList textList;
    private final Set<Condition> conditions;

    public Answer(String id, TextList textList, Set<Condition> conditions) {
        this.id = id;
        this.textList = textList;
        this.conditions = conditions;
    }

    public String getId() {
        return this.id;
    }

    public TextList getTextList() {
        return this.textList;
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
                && Objects.equals(this.textList, other.textList)
                && Objects.equals(this.conditions, other.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.textList, this.conditions);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + this.id + '\'' +
                ", textList=" + this.textList +
                ", conditions=" + this.conditions +
                '}';
    }
}
