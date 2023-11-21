package dev.kafein.interactivenpcs.npc;

import java.util.Objects;

public final class InteractiveNpc {
    private final int id;
    private final Focus focus;
    private final Speech speech;

    public InteractiveNpc(int id, Focus focus, Speech speech) {
        this.id = id;
        this.focus = focus;
        this.speech = speech;
    }

    public static InteractiveNpc of(int id, Speech speech) {
        return new InteractiveNpc(id, Focus.defaultFocus(), speech);
    }

    public static InteractiveNpc of(int id, Focus focus, Speech speech) {
        return new InteractiveNpc(id, focus, speech);
    }

    public int getId() {
        return this.id;
    }

    public Focus getFocus() {
        return this.focus;
    }

    public Speech getSpeech() {
        return this.speech;
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
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.focus, this.speech);
    }

    @Override
    public String toString() {
        return "InteractiveNpc{" +
                "id=" + this.id +
                ", focus=" + this.focus +
                ", speech=" + this.speech +
                '}';
    }
}
