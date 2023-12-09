package dev.kafein.interactivenpcs.npc;

import dev.kafein.interactivenpcs.conversation.Conversation;

import java.util.Objects;

public final class InteractiveNpc {
    private final NpcProperties properties;
    private final Focus focus;
    private final Conversation conversation;

    public InteractiveNpc(NpcProperties properties, Focus focus, Conversation conversation) {
        this.properties = properties;
        this.focus = focus;
        this.conversation = conversation;
    }

    public static InteractiveNpc of(NpcProperties properties, Conversation conversation) {
        return new InteractiveNpc(properties, Focus.defaultFocus(), conversation);
    }

    public static InteractiveNpc of(NpcProperties properties, Focus focus, Conversation conversation) {
        return new InteractiveNpc(properties, focus, conversation);
    }

    public NpcProperties getProperties() {
        return this.properties;
    }

    public Focus getFocus() {
        return this.focus;
    }

    public Conversation getSpeech() {
        return this.conversation;
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
        return Objects.equals(this.properties, other.properties)
                && Objects.equals(this.focus, other.focus)
                && Objects.equals(this.conversation, other.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.properties, this.focus, this.conversation);
    }

    @Override
    public String toString() {
        return "InteractiveNpc{" +
                "properties=" + this.properties +
                ", focus=" + this.focus +
                ", speech=" + this.conversation +
                '}';
    }
}
