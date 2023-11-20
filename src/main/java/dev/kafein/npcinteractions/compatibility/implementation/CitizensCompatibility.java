package dev.kafein.npcinteractions.compatibility.implementation;

import dev.kafein.npcinteractions.NpcInteractions;
import dev.kafein.npcinteractions.compatibility.Compatibility;
import dev.kafein.npcinteractions.interaction.Interaction;
import dev.kafein.npcinteractions.interaction.TargetNpc;
import dev.kafein.npcinteractions.npc.InteractiveNpc;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public final class CitizensCompatibility implements Compatibility, Listener {
    private final NpcInteractions plugin;

    public CitizensCompatibility(NpcInteractions plugin) {
        this.plugin = plugin;
    }

    @Override
    public void initialize() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, this.plugin.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(NPCClickEvent event) {
        NPC npc = event.getNPC();

        InteractiveNpc interactiveNpc = this.plugin.getInteractionManager().getNpcs().getIfPresent(npc.getId());
        if (interactiveNpc == null) {
            return;
        }

        TargetNpc targetNpc = new TargetNpc(npc.getId(), npc.getEntity().getLocation().clone().add(0, 1.5, 0));
        this.plugin.getInteractionManager().interact(event.getClicker(), targetNpc);
    }
}
