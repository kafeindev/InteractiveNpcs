package dev.kafein.interactivenpcs.conversation.text.font;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.kafein.interactivenpcs.InteractiveNpcs;

import java.util.Optional;

public final class CharWidthMap {
    public static final int DEFAULT_WIDTH = 3;

    private final InteractiveNpcs plugin;
    private final Cache<Character, Integer> widths;

    public CharWidthMap(InteractiveNpcs plugin) {
        this.plugin = plugin;
        this.widths = CacheBuilder.newBuilder()
                .build();
    }

    public void initialize() {
        CharWidth[] values = CharWidth.values();
        for (CharWidth value : values) {
            this.widths.put(value.getCharacter(), value.getWidth());
        }

        //load from configs

        this.plugin.getLogger().info("CharWidthMap initialized.");
    }

    public Cache<Character, Integer> getWidths() {
        return this.widths;
    }

    public int get(Character character) {
        return Optional.ofNullable(this.widths.getIfPresent(character))
                .orElse(DEFAULT_WIDTH);
    }

    public void put(Character character, int width) {
        this.widths.put(character, width);
    }

    public void remove(Character character) {
        this.widths.invalidate(character);
    }
}
