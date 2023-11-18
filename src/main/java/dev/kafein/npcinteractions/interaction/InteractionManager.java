package dev.kafein.npcinteractions.interaction;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.npcinteractions.NpcInteractions;

import java.util.UUID;

public final class InteractionManager {
    private final NpcInteractions plugin;

    private final Cache<Integer, Interaction> interactions;
    private final Cache<UUID, Interact> interacts;

    public InteractionManager(NpcInteractions plugin) {
        this.plugin = plugin;
        this.interactions = CacheBuilder.newBuilder()
                .build();
        this.interacts = CacheBuilder.newBuilder()
                .build();
    }

    public Cache<Integer, Interaction> getInteractions() {
        return this.interactions;
    }

    public Cache<UUID, Interact> getInteracts() {
        return this.interacts;
    }
}
