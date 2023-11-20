package dev.kafein.npcinteractions.interaction;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.npc.InteractiveNpc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class InteractionManager {
    private final NpcInteractions plugin;

    private final Cache<UUID, Interaction> interactions;
    private final Cache<Integer, InteractiveNpc> npcs;

    public InteractionManager(NpcInteractions plugin) {
        this.plugin = plugin;
        this.interactions = CacheBuilder.newBuilder()
                .build();
        this.npcs = CacheBuilder.newBuilder()
                .build();
    }

    public void interact(UUID uuid, int npcId) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            interact(player, npcId);
        }
    }

    public void interact(Player player, int npcId) {

    }

    public void interact(Interaction interact) {

    }

    public Cache<UUID, Interaction> getInteractions() {
        return this.interactions;
    }

    public Cache<Integer, InteractiveNpc> getNpcs() {
        return this.npcs;
    }
}
