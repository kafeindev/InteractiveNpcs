package dev.kafein.interactivenpcs.conversation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class ConversationManager {
    private final InteractiveNpcs plugin;

    private final Cache<String, InteractiveEntity> interactiveEntities;
    private final Cache<UUID, Conversation> conversations;

    public ConversationManager(@NotNull InteractiveNpcs plugin) {
        this.plugin = plugin;
        this.interactiveEntities = CacheBuilder.newBuilder()
                .build();
        this.conversations = CacheBuilder.newBuilder()
                .build();
    }

    public void initialize() {

    }

    public Cache<String, InteractiveEntity> getInteractiveEntities() {
        return this.interactiveEntities;
    }

    public InteractiveEntity getInteractiveEntity(String name) {
        return this.interactiveEntities.getIfPresent(name);
    }

    public void putInteractiveEntity(InteractiveEntity interactiveEntity) {
        this.interactiveEntities.put(interactiveEntity.getName(), interactiveEntity);
    }

    public void removeInteractiveEntity(String name) {
        this.interactiveEntities.invalidate(name);
    }

    public Cache<UUID, Conversation> getConversations() {
        return this.conversations;
    }

    public Conversation getConversation(UUID uuid) {
        return this.conversations.getIfPresent(uuid);
    }

    public void putConversation(Conversation conversation) {
        this.conversations.put(conversation.getInteractantUniqueId(), conversation);
    }

    public void removeConversation(UUID uuid) {
        this.conversations.invalidate(uuid);
    }
}
