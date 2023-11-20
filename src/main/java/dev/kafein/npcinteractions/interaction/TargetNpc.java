package dev.kafein.npcinteractions.interaction;

import org.bukkit.Location;

import java.util.Objects;

public final class TargetNpc {
    private final int id;
    private final Location eyeLocation;

    public TargetNpc(int id, Location eyeLocation) {
        this.id = id;
        this.eyeLocation = eyeLocation;
    }

    public int getId() {
        return this.id;
    }

    public Location getEyeLocation() {
        return this.eyeLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TargetNpc)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        TargetNpc other = (TargetNpc) obj;
        return other.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.eyeLocation);
    }

    @Override
    public String toString() {
        return "TargetNpc{" +
                "id=" + this.id +
                ", eyeLocation=" + this.eyeLocation +
                '}';
    }
}
