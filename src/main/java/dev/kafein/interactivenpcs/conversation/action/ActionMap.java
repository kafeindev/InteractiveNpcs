package dev.kafein.interactivenpcs.conversation.action;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import dev.kafein.interactivenpcs.InteractiveNpcs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Server;

import static org.apache.commons.lang.text.StrSubstitutor.replace;

public final class ActionMap {
    private final InteractiveNpcs plugin;
    private final Cache<String, Action> actions;

    public ActionMap(InteractiveNpcs plugin) {
        this.plugin = plugin;
        this.actions = CacheBuilder.newBuilder()
                .build();
    }

    public void initialize() {
        this.actions.put("transition", (player, value) -> {
            //
        });
        this.actions.put("skip", (player, value) -> {
            //
        });
        this.actions.put("player-command", (player, value) -> {
            String command = replace(value, ImmutableMap.of("%player%", player.getName()));
            player.performCommand(command);
        });
        this.actions.put("console-command", (player, value) -> {
            String command = replace(value, ImmutableMap.of("%player%", player.getName()));
            Server server = player.getServer();
            server.dispatchCommand(server.getConsoleSender(), command);
        });
        this.actions.put("message", (player, value) -> {
            Component message = MiniMessage.miniMessage().deserialize(value);
            player.sendMessage(message);
        });
    }

    public Cache<String, Action> getActions() {
        return this.actions;
    }

    public Action get(String name) {
        return this.actions.getIfPresent(name);
    }

    public void register(String name, Action action) {
        this.actions.put(name, action);
    }

    public void unregister(String name) {
        this.actions.invalidate(name);
    }
}
