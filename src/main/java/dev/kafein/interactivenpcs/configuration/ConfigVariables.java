package dev.kafein.interactivenpcs.configuration;

import com.google.common.collect.ImmutableList;

import java.util.List;

public final class ConfigVariables {
    private ConfigVariables() {}

    public static final ConfigKey<String> OFFSET_SYMBOL = ConfigKey.of("%img_offset_-1%", "offset-symbol");

    public static final ConfigKey<List<String>> SPEECH_BUBBLE_LINES_FONTS = ConfigKey.of(ImmutableList.of(), "speech", "bubble-lines", "fonts");
    public static final ConfigKey<String> SPEECH_BUBBLE_LINES_DEFAULT_COLOR = ConfigKey.of("<black>", "speech", "bubble-lines", "default-color");
    public static final ConfigKey<Integer> SPEECH_BUBBLE_LINES_DEFAULT_OFFSET = ConfigKey.of(0, "speech", "bubble-lines", "default-offset");
    public static final ConfigKey<String> SPEECH_BUBBLE_LINES_DEFAULT_IMAGE_SYMBOL = ConfigKey.of("%img_bubble%", "speech", "bubble-lines", "image", "default-symbol");
    public static final ConfigKey<Integer> SPEECH_BUBBLE_LINES_DEFAULT_IMAGE_WIDTH = ConfigKey.of(0, "speech", "bubble-lines", "image", "default-width");
}
