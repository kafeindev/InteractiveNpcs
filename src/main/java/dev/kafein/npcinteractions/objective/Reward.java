package dev.kafein.npcinteractions.objective;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static dev.kafein.npcinteractions.utils.Strings.replace;

public final class Reward {
    private final List<String> commands;
    private final List<ItemStack> items;

    public Reward(List<String> commands, List<ItemStack> items) {
        this.commands = commands;
        this.items = items;
    }

    public void apply(@NotNull Player player) {
        applyCommands(player);
        applyItems(player);
    }

    public void applyCommands(@NotNull Player player) {
        Server server = player.getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        Map<String, String> replacements = ImmutableMap.of(
                "%player%", player.getName(),
                "%uuid%", player.getUniqueId().toString()
        );
        this.commands.forEach(command -> {
            String replacedCommand = replace(command, replacements);
            server.dispatchCommand(console, replacedCommand);
        });
    }

    public void applyItems(@NotNull Player player) {
        Map<Integer, ItemStack> remaining = player.getInventory().addItem(this.items.toArray(new ItemStack[0]));
        if (remaining.isEmpty()) {
            return;
        }

        World world = player.getWorld();
        Location location = player.getLocation();
        remaining.values().forEach(item -> world.dropItem(location, item));
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
