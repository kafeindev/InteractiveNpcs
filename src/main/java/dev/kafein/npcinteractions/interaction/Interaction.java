package dev.kafein.npcinteractions.interaction;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

public final class Interaction {
    private final InteractiveNpc interactiveNpc;
    private final Focus focus;
    private final Map<Integer, InteractionStage> stages;

    public Interaction(InteractiveNpc interactiveNpc, Focus focus, Map<Integer, InteractionStage> stages) {
        this.interactiveNpc = interactiveNpc;
        this.focus = focus;
        this.stages = stages;
    }

    public static Interaction of(InteractiveNpc interactiveNpc) {
        return new Interaction(interactiveNpc, Focus.defaultFocus(), Maps.newHashMap());
    }

    public static Interaction of(InteractiveNpc interactiveNpc, Focus focus) {
        return new Interaction(interactiveNpc, focus, Maps.newHashMap());
    }

    public static Interaction of(InteractiveNpc interactiveNpc, Focus focus, Map<Integer, InteractionStage> stages) {
        return new Interaction(interactiveNpc, focus, stages);
    }

    public InteractiveNpc getInteractiveNpc() {
        return this.interactiveNpc;
    }

    public Focus getFocus() {
        return this.focus;
    }

    public Map<Integer, InteractionStage> getStages() {
        return this.stages;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Interaction)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Interaction other = (Interaction) obj;
        return this.interactiveNpc.equals(other.interactiveNpc)
                && this.focus.equals(other.focus)
                && this.stages.equals(other.stages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.interactiveNpc, this.focus, this.stages);
    }

    @Override
    public String toString() {
        return "Interaction{"
                + "interactiveNpc=" + this.interactiveNpc
                + ", focus=" + this.focus
                + ", stages=" + this.stages
                + "}";
    }
}
