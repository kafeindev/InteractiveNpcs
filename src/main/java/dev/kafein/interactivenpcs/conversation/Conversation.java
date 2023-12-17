package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.conversation.text.TextRenderer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public final class Conversation {
    private final int interactedEntityId;
    private final Interactant interactant;

    private TextRenderer textRenderer;

    public Conversation(int interactedEntityId, UUID playerUniqueId, String playerName, Location firstLocation) {
        this.interactedEntityId = interactedEntityId;
        this.interactant = Interactant.of(playerUniqueId, playerName, firstLocation);
    }

    public static Conversation of(int interactedEntityId, Player player) {
        return new Conversation(interactedEntityId, player.getUniqueId(), player.getName(), player.getLocation().clone());
    }

    public static Conversation of(int interactedEntityId, UUID playerUniqueId, String playerName, Location firstLocation) {
        return new Conversation(interactedEntityId, playerUniqueId, playerName, firstLocation);
    }

    public int getInteractedEntityId() {
        return this.interactedEntityId;
    }

    public Interactant getInteractant() {
        return this.interactant;
    }

    public UUID getInteractantUniqueId() {
        return this.interactant.getUniqueId();
    }

    public String getInteractantName() {
        return this.interactant.getName();
    }

    public Location getFirstLocation() {
        return this.interactant.getFirstLocation();
    }

    public Location getLastLocation() {
        return this.interactant.getLastLocation();
    }

    public void setLastLocation(Location lastLocation) {
        this.interactant.setLastLocation(lastLocation);
    }

    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Conversation)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Conversation other = (Conversation) obj;
        return this.interactedEntityId == other.interactedEntityId
                && this.interactant.equals(other.interactant)
                && Objects.equals(this.textRenderer, other.textRenderer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.interactedEntityId, this.interactant, this.textRenderer);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "interactedEntityId=" + this.interactedEntityId +
                ", interactant=" + this.interactant +
                ", textRenderer=" + this.textRenderer +
                '}';
    }
}
