package dev.kafein.npcinteractions.tasks;

import com.google.common.cache.Cache;
import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.interaction.Interaction;
import dev.kafein.npcinteractions.interaction.TargetNpc;
import dev.kafein.npcinteractions.npc.InteractiveNpc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class PositionCheckTask implements Runnable {
    private final NpcInteractions plugin;

    public PositionCheckTask(NpcInteractions plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Cache<UUID, Interaction> interactionCache = this.plugin.getInteractionManager().getInteractions();
        interactionCache.asMap().forEach((uuid, interact) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                interactionCache.invalidate(uuid);
                return;
            }

            TargetNpc targetNpc = interact.getTargetNpc();

            InteractiveNpc npc = this.plugin.getInteractionManager().getNpcs().getIfPresent(targetNpc.getId());
            if (npc == null) {
                //

                interactionCache.invalidate(uuid);
                return;
            }

            Location playerLocation = player.getLocation();
            if (playerLocation.distance(interact.getFirstLocation()) > npc.getFocus().getMaxDistance()) {
                //

                interactionCache.invalidate(uuid);
                return;
            }
        });
    }
}
