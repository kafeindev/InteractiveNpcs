package dev.kafein.interactivenpcs.direction;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class Direction implements Cloneable {
    public static final float EPSILON = 0.1f;

    private final float yaw;
    private final float pitch;

    public Direction(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Direction(Location location) {
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public Direction(Location location, Location targetLocation) {
        Vector vector = targetLocation.toVector().subtract(location.toVector()).normalize();
        this.yaw = (float) Math.toDegrees(Math.atan2(-vector.getX(), vector.getZ()));
        this.pitch = (float) Math.toDegrees(Math.asin(-vector.getY()));
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public Direction add(Direction direction) {
        return new Direction(this.yaw + direction.getYaw(), this.pitch + direction.getPitch());
    }

    public Direction subtract(Direction direction) {
        return new Direction(this.yaw - direction.getYaw(), this.pitch - direction.getPitch());
    }

    public Direction multiply(float multiplier) {
        return new Direction(this.yaw * multiplier, this.pitch * multiplier);
    }

    public Direction divide(float divisor) {
        return new Direction(this.yaw / divisor, this.pitch / divisor);
    }

    public Direction interpolate(Direction target, float speed) {
        if (isNearly(target, speed)) {
            return target;
        }

        Direction direction = target.subtract(this).normalize().multiply(speed);
        return this.add(direction).normalize();
    }

    public Direction normalize() {
        float yaw = this.yaw % 360;
        float pitch = this.pitch % 360;

        if (yaw > 180) {
            yaw -= 360;
        } else if (yaw < -180) {
            yaw += 360;
        }

        if (pitch > 90) {
            pitch -= 180;
        } else if (pitch < -90) {
            pitch += 180;
        }

        return new Direction(yaw, pitch);
    }

    public float distance(Direction target) {
        float deltaYaw = target.getYaw() - this.yaw;
        float deltaPitch = target.getPitch() - this.pitch;

        if (deltaYaw > 180) {
            deltaYaw -= 360;
        } else if (deltaYaw < -180) {
            deltaYaw += 360;
        }

        if (deltaPitch > 90) {
            deltaPitch -= 180;
        } else if (deltaPitch < -90) {
            deltaPitch += 180;
        }

        return (float) Math.sqrt(deltaYaw * deltaYaw + deltaPitch * deltaPitch);
    }

    public boolean isValidAngle() {
        return this.yaw >= -180 && this.yaw <= 180 && this.pitch >= -90 && this.pitch <= 90;
    }

    public boolean isNearly(Direction target) {
        return this.isNearly(target, EPSILON);
    }

    public boolean isNearly(Direction target, float epsilon) {
        return Math.abs(this.yaw - target.getYaw()) <= epsilon && Math.abs(this.pitch - target.getPitch()) <= epsilon;
    }

    @Override
    public Direction clone() {
        return new Direction(this.yaw, this.pitch);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Direction)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Direction target = (Direction) obj;
        return this.yaw == target.getYaw() && this.pitch == target.getPitch();
    }

    @Override
    public int hashCode() {
        return Float.hashCode(this.yaw) ^ Float.hashCode(this.pitch);
    }

    @Override
    public String toString() {
        return "Direction{yaw=" + this.yaw + ", pitch=" + this.pitch + "}";
    }
}
