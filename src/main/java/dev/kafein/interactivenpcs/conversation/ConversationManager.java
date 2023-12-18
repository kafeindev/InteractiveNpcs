package dev.kafein.interactivenpcs.conversation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class ConversationManager {
    private final InteractiveNpcs plugin;

    private final Cache<Integer, InteractiveEntity> interactiveEntities;
    private final Cache<UUID, Conversation> conversations;

    public ConversationManager(InteractiveNpcs plugin) {
        this.plugin = plugin;
        this.interactiveEntities = CacheBuilder.newBuilder()
                .build();
        this.conversations = CacheBuilder.newBuilder()
                .build();
    }

    public void initialize() {

    }

    public void interact(@NotNull UUID interactantUniqueId) {
        Player player = Bukkit.getPlayer(interactantUniqueId);
        if (player != null) {
            this.interact(player);
        }
    }

    public void interact(@NotNull Player player) {

    }

    public void interact(@NotNull Conversation conversation) {

    }

    public Cache<Integer, InteractiveEntity> getInteractiveEntities() {
        return this.interactiveEntities;
    }

    public InteractiveEntity getInteractiveEntity(int name) {
        return this.interactiveEntities.getIfPresent(name);
    }

    public void putInteractiveEntity(@NotNull InteractiveEntity interactiveEntity) {
        this.interactiveEntities.put(interactiveEntity.getId(), interactiveEntity);
    }

    public void removeInteractiveEntity(int name) {
        this.interactiveEntities.invalidate(name);
    }

    public Cache<UUID, Conversation> getConversations() {
        return this.conversations;
    }

    public Conversation getConversation(@NotNull UUID uuid) {
        return this.conversations.getIfPresent(uuid);
    }

    public void putConversation(@NotNull Conversation conversation) {
        this.conversations.put(conversation.getInteractantUniqueId(), conversation);
    }

    public void removeConversation(@NotNull UUID uuid) {
        this.conversations.invalidate(uuid);
    }
}
