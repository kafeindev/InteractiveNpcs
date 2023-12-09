package dev.kafein.interactivenpcs.interaction;

import com.comphenix.protocol.events.PacketContainer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.npc.Focus;
import dev.kafein.interactivenpcs.npc.InteractiveNpc;
import dev.kafein.interactivenpcs.npc.NpcProperties;
import dev.kafein.interactivenpcs.packet.PacketContainerFactory;
import dev.kafein.interactivenpcs.conversation.Conversation;
import dev.kafein.interactivenpcs.conversation.ConversationType;
import dev.kafein.interactivenpcs.conversation.ConversationWriter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class InteractionManager {
    private final InteractiveNpcs plugin;

    private final Cache<UUID, Interaction> interactions;
    private final Cache<Integer, InteractiveNpc> npcs;

    public InteractionManager(InteractiveNpcs plugin) {
        this.plugin = plugin;
        this.interactions = CacheBuilder.newBuilder()
                .build();
        this.npcs = CacheBuilder.newBuilder()
                .build();
    }

    public void initialize() {

    }

    public void interact(@NotNull UUID uuid, @NotNull NpcProperties npcProperties) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            interact(player, npcProperties);
        }
    }

    public void interact(@NotNull Player player, @NotNull NpcProperties npcProperties) {
        Interaction interaction = this.interactions.getIfPresent(player.getUniqueId());
        if (interaction != null) {
            interact(player, interaction);
        } else {
            Interaction newInteraction = Interaction.of(player, npcProperties);
            this.interactions.put(player.getUniqueId(), newInteraction);

            interact(player, newInteraction);
        }
    }

    public void interact(@NotNull Player player, @NotNull Interaction interaction) {
        InteractiveNpc interactiveNpc = this.npcs.getIfPresent(interaction.getNpcId());
        if (interactiveNpc == null) {
            invalidate(player.getUniqueId());
            return;
        }

        Focus focus = interactiveNpc.getFocus();
        if (interaction.getFirstLocation().distance(player.getLocation().clone()) > focus.getMaxDistance()) {
            invalidate(player.getUniqueId());
            return;
        }

        if (!interaction.isFocused()) {
            PacketContainer packetContainer = PacketContainerFactory.createAbilities(focus.getFov(), player.getFlySpeed(), player.getAllowFlight(), player.isFlying());
            this.plugin.getProtocolManager().sendServerPacket(player, packetContainer);
        }

        Conversation conversation = interactiveNpc.getSpeech();
        if (conversation == null) {
            return;
        }

        if (interaction.increaseClickCount() == 1) {
            ConversationWriter.runTask(this.plugin, interaction, conversation);
            return;
        }

        SpeechStage speechStage = conversation.getStage(interaction.getSpeechStage());
        if (speechStage == null) {
            invalidate(player.getUniqueId());
            return;
        }

        if (conversation.getType() == ConversationType.BUBBLE && !interaction.isAllLinesWritten()) {
            List<String> lines = new ArrayList<>(speechStage.getLines());
            interaction.setWrittenLines(lines);
            return;
        }

        if (speechStage.getConditions() != null) {
            if (!speechStage.testConditions(player)) {
                invalidate(player.getUniqueId());
                return;
            }

            speechStage.applyConditions(player);
        }

        speechStage.applyRewards(player);

        interaction.increaseSpeechStage();
        interaction.setAllLinesWritten(false);
        interaction.setWrittenLines(null);

        ConversationWriter.runTask(this.plugin, interaction, conversation);
    }

    public void invalidate(@NotNull UUID uuid) {
        this.interactions.invalidate(uuid);

        Player player = Bukkit.getPlayer(uuid);
        if (player == null || !player.isOnline()) {
            return;
        }

        player.removeMetadata("npc-interaction", this.plugin.getPlugin());

        PacketContainer packetContainer = PacketContainerFactory.createAbilities(0.0f, player.getFlySpeed(), player.getAllowFlight(), player.isFlying());
        this.plugin.getProtocolManager().sendServerPacket(player, packetContainer);
    }

    public Cache<UUID, Interaction> getInteractions() {
        return this.interactions;
    }

    public Cache<Integer, InteractiveNpc> getNpcs() {
        return this.npcs;
    }
}
