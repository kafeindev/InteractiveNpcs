package dev.kafein.interactivenpcs.interaction;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Interaction {
    private final UUID playerUniqueId;
    private final Location firstLocation;
    private final TargetNpc targetNpc;

    private int clickCount;
    private boolean focused;

    private int speechStage;
    private List<String> writtenTexts;

    private Location lastLocation;

    public Interaction(UUID playerUniqueId, Location firstLocation, TargetNpc targetNpc) {
        this.playerUniqueId = playerUniqueId;
        this.firstLocation = firstLocation;
        this.targetNpc = targetNpc;
        this.lastLocation = firstLocation;
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

    public int getClickCount() {
        return this.clickCount;
    }

    public int increaseClickCount() {
        return this.clickCount++;
    }

    public boolean isFocused() {
        return this.focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public int getSpeechStage() {
        return this.speechStage;
    }

    public void setSpeechStage(int speechStage) {
        this.speechStage = speechStage;
    }

    public @Nullable List<String> getWrittenTexts() {
        return this.writtenTexts;
    }

    public void setWrittenTexts(@Nullable List<String> writtenTexts) {
        this.writtenTexts = writtenTexts;
    }

    public Location getLastLocation() {
        return this.lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
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
        return Objects.equals(this.playerUniqueId, other.playerUniqueId)
                && Objects.equals(this.firstLocation, other.firstLocation)
                && Objects.equals(this.targetNpc, other.targetNpc)
                && this.clickCount == other.clickCount
                && this.focused == other.focused
                && this.speechStage == other.speechStage
                && Objects.equals(this.writtenTexts, other.writtenTexts)
                && Objects.equals(this.lastLocation, other.lastLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerUniqueId, this.firstLocation, this.targetNpc, this.clickCount, this.focused, this.speechStage, this.writtenTexts, this.lastLocation);
    }

    @Override
    public String toString() {
        return "Interaction{"
                + "playerUniqueId=" + this.playerUniqueId
                + ", firstLocation=" + this.firstLocation
                + ", targetNpc=" + this.targetNpc
                + ", clickCount=" + this.clickCount
                + ", focused=" + this.focused
                + ", speechStage=" + this.speechStage
                + ", writtenTexts=" + this.writtenTexts
                + ", lastLocation=" + this.lastLocation
                + "}";
    }
}
