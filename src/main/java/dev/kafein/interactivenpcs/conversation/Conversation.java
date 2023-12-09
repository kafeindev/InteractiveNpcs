package dev.kafein.interactivenpcs.conversation;

import java.util.Objects;

public final class Conversation {
    private final String name;
    private final ConversationOptions options;
    private final DialogueMap dialogueMap;

    public Conversation(String name, ConversationOptions options) {
        this(name, options, new DialogueMap());
    }

    public Conversation(String name, ConversationOptions options, DialogueMap dialogueMap) {
        this.name = name;
        this.options = options;
        this.dialogueMap = dialogueMap;
    }

    public static Conversation of(String name, ConversationOptions options) {
        return new Conversation(name, options);
    }

    public static Conversation of(String name, ConversationOptions options, DialogueMap dialogueMap) {
        return new Conversation(name, options, dialogueMap);
    }

    public String getName() {
        return this.name;
    }

    public ConversationOptions getOptions() {
        return this.options;
    }

    public DialogueMap getDialogueMap() {
        return this.dialogueMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Conversation)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Conversation other = (Conversation) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.options, other.options)
                && Objects.equals(this.dialogueMap, other.dialogueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.options, this.dialogueMap);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "name='" + this.name + '\'' +
                ", options=" + this.options +
                ", dialogueMap=" + this.dialogueMap +
                '}';
    }
}
