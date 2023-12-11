package dev.kafein.interactivenpcs.conversation;

import java.util.Objects;

public final class ConversableNpc implements Conversable {
    private final int id;
    private final String name;
    private final Conversation conversation;

    public ConversableNpc(int id, String name, Conversation conversation) {
        this.id = id;
        this.name = name;
        this.conversation = conversation;
    }

    public static ConversableNpc of(int id, String name, Conversation conversation) {
        return new ConversableNpc(id, name, conversation);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Conversation getConversation() {
        return this.conversation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConversableNpc)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        ConversableNpc other = (ConversableNpc) obj;
        return this.id == other.id
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.conversation, other.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.conversation);
    }

    @Override
    public String toString() {
        return "ConversableNpc{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", conversation=" + this.conversation +
                '}';
    }
}
