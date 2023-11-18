package dev.kafein.npcinteractions.interaction.figure;

import java.util.Objects;

public final class Focus {
    private final double fov; // between 1.0 and 10.0
    private final double maxDistance;

    public Focus(double fov, double maxDistance) {
        this.fov = fov;
        this.maxDistance = maxDistance;
    }

    public static Focus defaultFocus() {
        return new Focus(2.0, 3.0);
    }

    public static Focus of(double fov, double maxDistance) {
        return new Focus(fov, maxDistance);
    }

    public double getFov() {
        return this.fov;
    }

    public double getMaxDistance() {
        return this.maxDistance;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Focus)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Focus other = (Focus) obj;
        return this.fov == other.fov
                && this.maxDistance == other.maxDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fov, this.maxDistance);
    }

    @Override
    public String toString() {
        return "Focus{"
                + "fov=" + this.fov
                + ", maxDistance=" + this.maxDistance
                + "}";
    }
}
