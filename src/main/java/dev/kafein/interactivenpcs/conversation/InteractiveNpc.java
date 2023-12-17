package dev.kafein.interactivenpcs.conversation;

import java.util.Objects;

public final class InteractiveNpc implements InteractiveEntity {
    private final int id;
    private final String name;
    private final DialogueMap dialogueMap;

    public InteractiveNpc(int id, String name) {
        this(id, name, new DialogueMap());
    }

    public InteractiveNpc(int id, String name, DialogueMap dialogueMap) {
        this.id = id;
        this.name = name;
        this.dialogueMap = dialogueMap;
    }

    public static InteractiveNpc of(int id, String name) {
        return new InteractiveNpc(id, name);
    }

    public static InteractiveNpc of(int id, String name, DialogueMap dialogueMap) {
        return new InteractiveNpc(id, name, dialogueMap);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DialogueMap getDialogueMap() {
        return this.dialogueMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InteractiveNpc)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        InteractiveNpc other = (InteractiveNpc) obj;
        return this.id == other.id
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.dialogueMap, other.dialogueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.dialogueMap);
    }

    @Override
    public String toString() {
        return "InteractiveNpc{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", dialogueMap=" + this.dialogueMap +
                '}';
    }
}
