package dev.kafein.interactivenpcs.interaction;

import dev.kafein.interactivenpcs.npc.NpcProperties;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Interaction {
    private final UUID playerUniqueId;
    private final Location firstLocation;
    private final NpcProperties npcProperties;

    private int clickCount;
    private boolean focused;

    private int speechStage;
    private List<String> writtenLines;
    private boolean allLinesWritten;

    private Location lastLocation;

    public Interaction(UUID playerUniqueId, Location firstLocation, NpcProperties npcProperties) {
        this.playerUniqueId = playerUniqueId;
        this.firstLocation = firstLocation;
        this.npcProperties = npcProperties;
        this.lastLocation = firstLocation;
    }

    public static Interaction of(Player player, NpcProperties npcProperties) {
        return new Interaction(player.getUniqueId(), player.getLocation().clone(), npcProperties);
    }

    public static Interaction of(UUID playerUniqueId, Location firstLocation, NpcProperties npcProperties) {
        return new Interaction(playerUniqueId, firstLocation, npcProperties);
    }

    public UUID getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public NpcProperties getNpcProperties() {
        return this.npcProperties;
    }

    public int getNpcId() {
        return this.npcProperties.getId();
    }

    public int getClickCount() {
        return this.clickCount;
    }

    public int increaseClickCount() {
        return ++this.clickCount;
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

    public int increaseSpeechStage() {
        return ++this.speechStage;
    }

    public void setSpeechStage(int speechStage) {
        this.speechStage = speechStage;
    }

    public List<String> getWrittenLines() {
        return this.writtenLines;
    }

    public void setWrittenLines(List<String> writtenLines) {
        this.writtenLines = writtenLines;
    }

    public boolean isAllLinesWritten() {
        return this.allLinesWritten;
    }

    public void setAllLinesWritten(boolean allLinesWritten) {
        this.allLinesWritten = allLinesWritten;
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
                && Objects.equals(this.npcProperties, other.npcProperties)
                && this.clickCount == other.clickCount
                && this.focused == other.focused
                && this.speechStage == other.speechStage
                && Objects.equals(this.writtenLines, other.writtenLines)
                && this.allLinesWritten == other.allLinesWritten
                && Objects.equals(this.lastLocation, other.lastLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerUniqueId, this.firstLocation, this.npcProperties, this.clickCount, this.focused, this.speechStage, this.writtenLines, this.allLinesWritten, this.lastLocation);
    }

    @Override
    public String toString() {
        return "Interaction{"
                + "playerUniqueId=" + this.playerUniqueId
                + ", firstLocation=" + this.firstLocation
                + ", npcProperties=" + this.npcProperties
                + ", clickCount=" + this.clickCount
                + ", focused=" + this.focused
                + ", speechStage=" + this.speechStage
                + ", writtenLines=" + this.writtenLines
                + ", allLinesWritten=" + this.allLinesWritten
                + ", lastLocation=" + this.lastLocation
                + "}";
    }
}
