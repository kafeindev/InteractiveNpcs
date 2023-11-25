package dev.kafein.interactivenpcs.direction;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class DirectionAdjuster extends BukkitRunnable {
    private final Plugin plugin;

    private final Player player;
    private final Direction targetDirection;
    private final float speed;

    private Direction currentDirection;

    public DirectionAdjuster(Plugin plugin, Player player, Direction targetDirection, float speed) {
        this.plugin = plugin;
        this.player = player;
        this.targetDirection = targetDirection;
        this.speed = speed;
        this.currentDirection = new Direction(player.getLocation());
    }

    @Override
    public void run() {
        if (!this.player.isOnline() || !this.player.hasMetadata("npc-interaction")) {
            cancel();
            return;
        }

        Location playerLocation = this.player.getLocation();
        Direction direction = new Direction(playerLocation);

        if (Math.abs(this.currentDirection.distance(direction)) % 360 >= Direction.EPSILON) {
            this.player.removeMetadata("npc-interaction", this.plugin);
            cancel();
            return;
        }

        float distance = direction.distance(this.targetDirection);
        float speed = Math.min(this.speed, distance / 2);

        if (direction.isNearly(this.targetDirection, speed / 2)) {
            if (!direction.equals(this.targetDirection)) {
                teleport(playerLocation, this.targetDirection);
            }

            this.player.removeMetadata("npc-interaction", this.plugin);
            cancel();
            return;
        }

        Direction newDirection = direction.interpolate(this.targetDirection, speed / distance);
        teleport(playerLocation, newDirection);

        this.currentDirection = newDirection;
    }

    private void teleport(Location location, Direction direction) {
        location.setYaw(direction.getYaw());
        location.setPitch(direction.getPitch());
        this.player.teleport(location);
    }
}
