package dev.kafein.interactivenpcs.compatibility.implementation;

import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.compatibility.Compatibility;
import dev.kafein.interactivenpcs.conversation.InteractiveEntity;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public final class CitizensCompatibility implements Compatibility, Listener {
    private final InteractiveNpcs plugin;

    public CitizensCompatibility(InteractiveNpcs plugin) {
        this.plugin = plugin;
    }

    @Override
    public void initialize() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, this.plugin.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(NPCRightClickEvent event) {
        NPC npc = event.getNPC();

        InteractiveEntity interactiveEntity = this.plugin.getConversationManager().getInteractiveEntity(npc.getId());
        if (interactiveEntity == null) {
            return;
        }

        /*Location eyeLocation = interactiveNpc.getProperties().getEyeLocation() == null
                ? npc.getEntity().getLocation().clone()
                : interactiveNpc.getProperties().getEyeLocation();

        NpcProperties npcProperties = new NpcProperties(npc.getId(), eyeLocation);
        this.plugin.getInteractionManager().interact(event.getClicker(), npcProperties);*/
    }
}
