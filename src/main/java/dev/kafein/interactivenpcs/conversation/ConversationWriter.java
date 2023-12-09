package dev.kafein.interactivenpcs.conversation;

import dev.kafein.interactivenpcs.InteractiveNpcs;
import dev.kafein.interactivenpcs.configuration.ConfigVariables;
import dev.kafein.interactivenpcs.interaction.Interaction;
import dev.kafein.interactivenpcs.utils.Strings;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ConversationWriter extends BukkitRunnable {
    private static final String RESET_TAG = "<reset>";

    private final InteractiveNpcs plugin;

    private final Interaction interaction;
    private final Conversation conversation;
    private final String offsetSymbol;

    private final Player player;
    private final Audience audience;

    public ConversationWriter(InteractiveNpcs plugin, Interaction interaction, Conversation conversation) {
        this.plugin = plugin;
        this.interaction = interaction;
        this.conversation = conversation;
        this.player = Bukkit.getPlayer(this.interaction.getPlayerUniqueId());
        this.audience = plugin.getBukkitAudiences().player(checkNotNull(this.player));
        this.offsetSymbol = PlaceholderAPI.setPlaceholders(this.player, ConfigVariables.OFFSET_SYMBOL.getValue());
    }

    public static void runTask(InteractiveNpcs plugin, Interaction interaction, Conversation conversation) {
        ConversationWriter writer = new ConversationWriter(plugin, interaction, conversation);
        writer.runTaskTimer(plugin.getPlugin(), 0L, 1L);
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            cancel();
            return;
        }

        if (this.plugin.getInteractionManager().getInteractions().getIfPresent(this.player.getUniqueId()) == null) {
            cancel();
            return;
        }
/*
        SpeechStage stage = this.conversation.getStage(this.interaction.getSpeechStage());
        if (stage == null) {
            cancel();
            return;
        }

        List<String> lines = new ArrayList<>(stage.getLines());

        ConversationType type = this.conversation.getType();
        if (type == ConversationType.CHAT) {
            String formatted = String.join("\n", lines);
            Component component = MiniMessage.miniMessage().deserialize(formatted);
            this.audience.sendMessage(component);

            this.interaction.setWrittenLines(lines);
            cancel();
            return;
        }

        List<String> writtenLines = Optional.ofNullable(this.interaction.getWrittenLines())
                .orElse(new ArrayList<>());
        if (writtenLines.equals(lines)) {
            this.interaction.setAllLinesWritten(true);
        } else {
            writeCharsFromLine(writtenLines, lines);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(RESET_TAG);
        builder.append(Strings.repeat(this.offsetSymbol, this.conversation.getImageWidth() / 2));
        builder.append(Strings.repeat(this.offsetSymbol, this.conversation.getLinesOffset()));
        builder.append(this.conversation.getImageSymbol());
        builder.append(Strings.repeat(this.offsetSymbol, this.conversation.getImageWidth() / 2));
        builder.append(Strings.repeat(this.offsetSymbol, this.conversation.getLinesOffset()));

        for (int i = 0; i < writtenLines.size(); i++) {
            String line = writtenLines.get(i);
            int width = 0;

            for (int j = 0; j < line.length(); j++) {
                char character = line.charAt(j);
                width += this.plugin.getCharWidthMap().get(character);

                builder.append(RESET_TAG);
                builder.append(this.offsetSymbol);
                builder.append(ConfigVariables.SPEECH_BUBBLE_LINES_FONTS.getValue().get(i));
                builder.append(ConfigVariables.SPEECH_BUBBLE_LINES_DEFAULT_COLOR.getValue());
                builder.append(character);
            }

            builder.append(RESET_TAG);
            builder.append(Strings.repeat(this.offsetSymbol, width));
        }

        Component component = MiniMessage.miniMessage().deserialize(builder.toString());
        Title.Times times = Title.Times.times(Duration.ZERO, Duration.ofSeconds(1), Duration.ZERO);

        Title title = Title.title(Component.empty(), component, times);
        this.audience.showTitle(title);*/
    }

    private void writeCharsFromLine(List<String> writtenLines, List<String> lines) {
        if (writtenLines.isEmpty()) {
            writtenLines.add("");
        }

        String line = writtenLines.get(writtenLines.size() - 1);
        String lineToWrite = lines.get(writtenLines.size() - 1);

        if (line.length() < lineToWrite.length()) {
            char character = lineToWrite.charAt(line.length());
            if (character == '<') {
                int index = lineToWrite.indexOf('>', line.length());
                if (index != -1) {
                    line = line + lineToWrite.substring(line.length(), index + 1);
                }
            } else if (character == '&' || character == '§'){
                line = line + lineToWrite.substring(line.length(), line.length() + 2);
            } else {
                line = line + character;
            }

            writtenLines.set(writtenLines.size() - 1, line);
        } else {
            writtenLines.add("");
        }
    }
}