package dev.kafein.interactivenpcs.npc;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class NpcProperties {
    private final int id;
    private final @Nullable Location eyeLocation;

    public NpcProperties(int id, @Nullable Location eyeLocation) {
        this.id = id;
        this.eyeLocation = eyeLocation;
    }

    public int getId() {
        return this.id;
    }

    public @Nullable Location getEyeLocation() {
        return this.eyeLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NpcProperties)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        NpcProperties other = (NpcProperties) obj;
        return other.id == this.id && Objects.equals(other.eyeLocation, this.eyeLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.eyeLocation);
    }

    @Override
    public String toString() {
        return "NpcProperties{" +
                "id=" + this.id +
                ", eyeLocation=" + this.eyeLocation +
                '}';
    }
}
