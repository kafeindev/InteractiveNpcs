package dev.kafein.interactivenpcs.conversation.action;

import org.bukkit.Location;

import java.util.Objects;

public final class Focus {
    private final Location target;
    private final float speed; // direction speed
    private final float fov; // between 1.0 and 10.0
    private final double maxDistance;

    public Focus(Location target, float speed, float fov, double maxDistance) {
        this.target = target;
        this.speed = speed;
        this.fov = fov;
        this.maxDistance = maxDistance;
    }

    public static Focus of(Location target, float speed, float fov, double maxDistance) {
        return new Focus(target, speed, fov, maxDistance);
    }

    public Location getTarget() {
        return this.target;
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
        return this.target.equals(other.target)
                && this.speed == other.speed
                && this.fov == other.fov
                && this.maxDistance == other.maxDistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.target, this.speed, this.fov, this.maxDistance);
    }

    @Override
    public String toString() {
        return "Focus{"
                + "target=" + this.target
                + ", speed=" + this.speed
                + ", fov=" + this.fov
                + ", maxDistance=" + this.maxDistance
                + "}";
    }
}
