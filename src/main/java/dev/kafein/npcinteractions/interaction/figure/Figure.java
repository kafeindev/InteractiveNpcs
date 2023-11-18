package dev.kafein.npcinteractions.interaction.figure;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Figure {
    private final int id;
    private @Nullable Location eyeLocation;

    public Figure(int id, @Nullable Location eyeLocation) {
        this.id = id;
        this.eyeLocation = eyeLocation;
    }

    public int getId() {
        return this.id;
    }

    public @Nullable Location getEyeLocation() {
        return this.eyeLocation;
    }

    public void setEyeLocation(@Nullable Location eyeLocation) {
        this.eyeLocation = eyeLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Figure)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Figure figure = (Figure) obj;
        return this.id == figure.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.eyeLocation);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "id=" + this.id +
                ", eyeLocation=" + this.eyeLocation +
                '}';
    }
}
