package dev.kafein.interactivenpcs.conversation;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class InteractiveNpc implements InteractiveEntity {
    private final int id;
    private final String name;
    private final DialogueMap dialogueMap;
    private final @Nullable Focus focus;

    public InteractiveNpc(int id, String name, @Nullable Focus focus) {
        this(id, name, new DialogueMap(), focus);
    }

    public InteractiveNpc(int id, String name, DialogueMap dialogueMap, @Nullable Focus focus) {
        this.id = id;
        this.name = name;
        this.dialogueMap = dialogueMap;
        this.focus = focus;
    }

    public static InteractiveNpc of(int id, String name, @Nullable Focus focus) {
        return new InteractiveNpc(id, name, focus);
    }

    public static InteractiveNpc of(int id, String name, DialogueMap dialogueMap, @Nullable Focus focus) {
        return new InteractiveNpc(id, name, dialogueMap, focus);
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
    public @Nullable Focus getFocus() {
        return this.focus;
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
                && Objects.equals(this.dialogueMap, other.dialogueMap)
                && Objects.equals(this.focus, other.focus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.dialogueMap, this.focus);
    }

    @Override
    public String toString() {
        return "InteractiveNpc{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", dialogueMap=" + this.dialogueMap +
                ", focus=" + this.focus +
                '}';
    }
}
