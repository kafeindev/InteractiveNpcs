package dev.kafein.npcinteractions.interaction;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public final class Interact {
    private final UUID playerUniqueId;
    private final Location firstLocation;
    private final int targetId;
    private final Location targetEyeLocation;

    public Interact(UUID playerUniqueId, Location firstLocation, int targetId, Location targetEyeLocation) {
        this.playerUniqueId = playerUniqueId;
        this.firstLocation = firstLocation;
        this.targetId = targetId;
        this.targetEyeLocation = targetEyeLocation;
    }

    public static Interact of(Player player, int targetId, Location targetEyeLocation) {
        return new Interact(player.getUniqueId(), player.getLocation().clone(), targetId, targetEyeLocation);
    }

    public static Interact of(UUID playerUniqueId, Location firstLocation, int targetId, Location targetEyeLocation) {
        return new Interact(playerUniqueId, firstLocation, targetId, targetEyeLocation);
    }

    public UUID getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public Location getTargetEyeLocation() {
        return this.targetEyeLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Interact)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Interact other = (Interact) obj;
        return this.playerUniqueId.equals(other.playerUniqueId)
                && this.firstLocation.equals(other.firstLocation)
                && this.targetId == other.targetId
                && this.targetEyeLocation.equals(other.targetEyeLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerUniqueId, this.firstLocation, this.targetId, this.targetEyeLocation);
    }

    @Override
    public String toString() {
        return "Interact{"
                + "playerUniqueId=" + this.playerUniqueId
                + ", firstLocation=" + this.firstLocation
                + ", targetId=" + this.targetId
                + ", targetEyeLocation=" + this.targetEyeLocation
                + "}";
    }
}
