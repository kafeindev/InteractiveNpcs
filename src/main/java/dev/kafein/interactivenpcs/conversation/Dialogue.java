package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.conversation.text.TextList;

import java.util.Objects;

public final class Dialogue {
    private final String id;
    private final TextList textList;
    private final AnswerMap answerMap;

    public Dialogue(String id, TextList textList) {
        this(id, textList, new AnswerMap());
    }

    public Dialogue(String id, TextList textList, AnswerMap answerMap) {
        this.id = id;
        this.textList = textList;
        this.answerMap = answerMap;
    }

    public static Dialogue of(String id, TextList textList) {
        return new Dialogue(id, textList);
    }

    public static Dialogue of(String id, TextList textList, AnswerMap answerMap) {
        return new Dialogue(id, textList, answerMap);
    }

    public String getId() {
        return this.id;
    }

    public TextList getTextList() {
        return this.textList;
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
                && Objects.equals(this.textList, other.textList)
                && Objects.equals(this.answerMap, other.answerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.textList, this.answerMap);
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + this.id + '\'' +
                ", textList=" + this.textList +
                ", answerMap=" + this.answerMap +
                '}';
    }
}
