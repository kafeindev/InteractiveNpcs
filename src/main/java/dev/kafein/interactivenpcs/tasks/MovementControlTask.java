package dev.kafein.interactivenpcs.tasks;

import com.google.common.cache.Cache;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.direction.Direction;
import dev.kafein.interactivenpcs.direction.DirectionAdjuster;
import dev.kafein.interactivenpcs.interaction.Interaction;
import dev.kafein.interactivenpcs.interaction.TargetNpc;
import dev.kafein.interactivenpcs.npc.Focus;
import dev.kafein.interactivenpcs.npc.InteractiveNpc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public final class MovementControlTask implements Runnable {
    private final InteractiveNpcs plugin;

    public MovementControlTask(InteractiveNpcs plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Cache<UUID, Interaction> interactionCache = this.plugin.getInteractionManager().getInteractions();
        interactionCache.asMap().forEach((uuid, interact) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                this.plugin.getInteractionManager().invalidate(uuid);
                return;
            }

            TargetNpc targetNpc = interact.getTargetNpc();

            InteractiveNpc interactiveNpc = this.plugin.getInteractionManager().getNpcs().getIfPresent(targetNpc.getId());
            if (interactiveNpc == null) {
                this.plugin.getInteractionManager().invalidate(uuid);
                return;
            }

            Focus focus = interactiveNpc.getFocus();
            Location playerLocation = player.getLocation().clone();
            if (playerLocation.distance(interact.getFirstLocation()) > focus.getMaxDistance()) {
                this.plugin.getInteractionManager().invalidate(uuid);
                return;
            }

            Location playerLastLocation = interact.getLastLocation();
            interact.setLastLocation(playerLocation);
            if (playerLocation.distance(playerLastLocation) > 0.1
                    || playerLastLocation.getYaw() != playerLocation.getYaw()
                    || playerLastLocation.getPitch() != playerLocation.getPitch()) {
                return;
            }

            if (player.hasMetadata("npc-interaction")) {
                return;
            }

            Location npcEyeLocation = targetNpc.getEyeLocation().clone();
            Direction direction = new Direction(playerLocation);
            Direction targetDirection = new Direction(playerLocation, npcEyeLocation);

            if (!targetDirection.isNearly(direction)) {
                player.setMetadata("npc-interaction", new FixedMetadataValue(this.plugin.getPlugin(), true));

                BukkitRunnable bukkitRunnable = new DirectionAdjuster(this.plugin.getPlugin(), player, targetDirection, focus.getSpeed());
                bukkitRunnable.runTaskTimer(this.plugin.getPlugin(), 0L, 1L);
            }
        });
    }
}
