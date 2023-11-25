package dev.kafein.interactivenpcs.interaction;

import com.comphenix.protocol.events.PacketContainer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.npc.*;
import dev.kafein.interactivenpcs.objective.Objective;
import dev.kafein.interactivenpcs.packet.PacketContainerFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

        this.npcs.put(0, InteractiveNpc.of(0, null)); // for test
    }

    public void interact(@NotNull UUID uuid, @NotNull TargetNpc targetNpc) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            interact(player, targetNpc);
        }
    }

    public void interact(@NotNull Player player, @NotNull TargetNpc targetNpc) {
        Interaction interaction = this.interactions.getIfPresent(player.getUniqueId());
        if (interaction != null) {
            interact(player, interaction);
        } else {
            Interaction newInteraction = Interaction.of(player, targetNpc);
            this.interactions.put(player.getUniqueId(), newInteraction);

            interact(player, newInteraction);
        }
    }

    public void interact(@NotNull Player player, @NotNull Interaction interaction) {
        InteractiveNpc interactiveNpc = this.npcs.getIfPresent(interaction.getTargetNpc().getId());
        if (interactiveNpc == null) {
            cancel(player.getUniqueId());
            return;
        }

        Focus focus = interactiveNpc.getFocus();
        if (interaction.getFirstLocation().distance(player.getLocation().clone()) > focus.getMaxDistance()) {
            cancel(player.getUniqueId());
            return;
        }

        if (!interaction.isFocused()) {
            PacketContainer packetContainer = PacketContainerFactory.createAbilities(focus.getFov(), player.getFlySpeed(), player.getAllowFlight(), player.isFlying());
            this.plugin.getProtocolManager().sendServerPacket(player, packetContainer);
        }

        if (interaction.increaseClickCount() == 1) { //check if it is the first click
            return;
        }

        Speech speech = interactiveNpc.getSpeech();
        if (speech == null) {
            //cancel(player.getUniqueId());
            return;
        }

        SpeechStage speechStage = speech.getStage(interaction.getSpeechStage());

        if (speech.getType() == SpeechType.BUBBLE) {
            //write all words if they are not written
            return;
        }

        Objective objective = speechStage.getObjective();
        if (objective == null) {
            //continue with next speech stage
            return;
        }

        if (!objective.testConditions(player)) {
            cancel(player.getUniqueId());
            return;
        }

        objective.applyConditions(player);
        objective.applyRewards(player);

        //continue with next speech stage
    }

    public void cancel(@NotNull UUID uuid) {
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
