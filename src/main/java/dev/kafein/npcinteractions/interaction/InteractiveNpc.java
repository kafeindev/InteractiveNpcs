package dev.kafein.npcinteractions.interaction;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class InteractiveNpc {
    private final int id;
    private @Nullable Location eyeLocation;

    public InteractiveNpc(int id, @Nullable Location eyeLocation) {
        this.id = id;
        this.eyeLocation = eyeLocation;
    }

    public static InteractiveNpc of(int id) {
        return new InteractiveNpc(id, null);
    }

    public static InteractiveNpc of(int id, @Nullable Location eyeLocation) {
        return new InteractiveNpc(id, eyeLocation);
    }

    public int getId() {
        return this.id;
    }

    public @Nullable Location getEyeLocation() {
        return this.eyeLocation;
    }

    public void setEyeLocation(@Nullable Location eyeLocation) {
        this.eyeLocation = eyeLocation;
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
        return Objects.hash(this.id, this.eyeLocation);
    }

    @Override
    public String toString() {
        return "InteractiveNpc{" +
                "id=" + this.id +
                ", eyeLocation=" + this.eyeLocation +
                '}';
    }
}
