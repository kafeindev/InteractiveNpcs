package dev.kafein.interactivenpcs.npc;

import java.util.Objects;

public final class Focus {
    private final float speed;
    private final float fov; // between 1.0 and 10.0
    private final double maxDistance;

    public Focus(float speed, float fov, double maxDistance) {
        this.speed = speed;
        this.fov = fov;
        this.maxDistance = maxDistance;
    }

    public static Focus defaultFocus() {
        return new Focus(10.0f, 0.5f, 3.0);
    }

    public static Focus of(float speed, float fov, double maxDistance) {
        return new Focus(speed, fov, maxDistance);
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getFov() {
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
        return this.speed == other.speed
                && this.fov == other.fov
                && this.maxDistance == other.maxDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.speed, this.fov, this.maxDistance);
    }

    @Override
    public String toString() {
        return "Focus{"
                + "speed=" + this.speed
                + ", fov=" + this.fov
                + ", maxDistance=" + this.maxDistance
                + "}";
    }
}
