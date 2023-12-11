package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.conversation.text.TextList;

import java.util.Objects;

public final class Dialogue {
    private final String id;
    private final int order;
    private final TextList textList;

    public Dialogue(String id, int order, TextList textList) {
        this.id = id;
        this.order = order;
        this.textList = textList;
    }

    public static Dialogue of(String id, int order, TextList textList) {
        return new Dialogue(id, order, textList);
    }

    public String getId() {
        return this.id;
    }

    public int getOrder() {
        return this.order;
    }

    public TextList getTextList() {
        return this.textList;
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
                && other.order == this.order
                && Objects.equals(this.textList, other.textList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.order, this.textList);
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + this.id + '\'' +
                ", order=" + this.order +
                ", textList=" + this.textList +
                '}';
    }
}
