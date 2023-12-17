package dev.kafein.interactivenpcs.conversation;

import org.bukkit.Location;

import java.util.UUID;

public final class Interactant {
    private final UUID uniqueId;
    private final String name;
    private final Location firstLocation;

    private Location lastLocation;

    public Interactant(UUID uniqueId, String name, Location firstLocation) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.firstLocation = firstLocation;
        this.lastLocation = firstLocation;
    }

    public static Interactant of(UUID uniqueId, String name, Location firstLocation) {
        return new Interactant(uniqueId, name, firstLocation);
    }

    public UUID getUniqueId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public Location getLastLocation() {
        return this.lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Interactant)) {
            return false;
        }
        if (this == o) {
            return true;
        }

        Interactant other = (Interactant) o;
        return this.uniqueId.equals(other.uniqueId);
    }

    @Override
    public int hashCode() {
        return this.uniqueId.hashCode();
    }

    @Override
    public String toString() {
        return "Interactant{"
                + "uniqueId=" + this.uniqueId
                + ", name=" + this.name
                + ", firstLocation=" + this.firstLocation
                + ", lastLocation=" + this.lastLocation
                + "}";
    }
}
