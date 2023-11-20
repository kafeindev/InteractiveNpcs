package dev.kafein.npcinteractions.objective;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public final class Reward {
    private final List<String> commands;
    private final List<ItemStack> items;

    public Reward(List<String> commands, List<ItemStack> items) {
        this.commands = commands;
        this.items = items;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public List<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Reward)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Reward other = (Reward) obj;
        return this.commands.equals(other.commands) && this.items.equals(other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.commands, this.items);
    }

    @Override
    public String toString() {
        return "Reward{"
                + "commands=" + this.commands
                + ", items=" + this.items
                + "}";
    }
}
