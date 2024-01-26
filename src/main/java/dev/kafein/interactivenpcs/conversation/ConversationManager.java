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

    public void interact(@NotNull UUID interactantUniqueId, int interactedEntityId) {
        Player player = Bukkit.getPlayer(interactantUniqueId);
        if (player != null) {
            this.interact(player, interactedEntityId);
        }
    }

    public void interact(@NotNull Player player, int interactedEntityId) {
        Conversation conversation = this.conversations.getIfPresent(player.getUniqueId());
        if (conversation != null) {
            interact(conversation);
        } else {
            Conversation newConversation = Conversation.of(interactedEntityId, player);
            this.conversations.put(player.getUniqueId(), newConversation);

            interact(newConversation);
        }
    }

    public void interact(@NotNull Conversation conversation) {
        
    }

    public void invalidate(@NotNull UUID interactantUniqueId) {
        this.conversations.invalidate(interactantUniqueId);

        Player player = Bukkit.getPlayer(interactantUniqueId);
        if (player != null) {
            this.invalidate(player);
        }
    }

    public void invalidate(@NotNull Player player) {
        this.conversations.invalidate(player.getUniqueId());
    }

    public void invalidate(@NotNull Conversation conversation) {
        invalidate(conversation.getInteractantUniqueId());
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
