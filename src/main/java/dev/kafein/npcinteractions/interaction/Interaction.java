package dev.kafein.npcinteractions.interaction;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Interaction {
    private final UUID playerUniqueId;
    private final Location firstLocation;
    private final TargetNpc targetNpc;

    private int clickCount = 1;
    private List<String> writtenTexts;

    public Interaction(UUID playerUniqueId, Location firstLocation, TargetNpc targetNpc) {
        this.playerUniqueId = playerUniqueId;
        this.firstLocation = firstLocation;
        this.targetNpc = targetNpc;
    }

    public static Interaction of(Player player, TargetNpc targetNpc) {
        return new Interaction(player.getUniqueId(), player.getLocation().clone(), targetNpc);
    }

    public static Interaction of(UUID playerUniqueId, Location firstLocation, TargetNpc targetNpc) {
        return new Interaction(playerUniqueId, firstLocation, targetNpc);
    }

    public UUID getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public TargetNpc getTargetNpc() {
        return this.targetNpc;
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
        return this.playerUniqueId.equals(other.playerUniqueId)
                && this.firstLocation.equals(other.firstLocation)
                && this.targetNpc.equals(other.targetNpc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerUniqueId, this.firstLocation, this.targetNpc);
    }

    @Override
    public String toString() {
        return "Interaction{"
                + "playerUniqueId=" + this.playerUniqueId
                + ", firstLocation=" + this.firstLocation
                + ", targetNpc=" + this.targetNpc
                + "}";
    }
}
