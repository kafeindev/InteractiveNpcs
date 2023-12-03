package dev.kafein.interactivenpcs.configuration;

import com.google.common.collect.ImmutableList;

import java.util.List;

public final class ConfigVariables {
    private ConfigVariables() {}

    public static final ConfigKey<String> OFFSET_SYMBOL = ConfigKey.of("%img_offset_-1%", "offset-symbol");

    public static final ConfigKey<List<String>> SPEECH_FONTS = ConfigKey.of(ImmutableList.of(), "speech", "fonts");
    public static final ConfigKey<String> SPEECH_BUBBLE_LINES_DEFAULT_COLOR = ConfigKey.of("<black>", "speech", "bubble", "lines-default-color");
}
