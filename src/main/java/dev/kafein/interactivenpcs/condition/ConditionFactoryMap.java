package dev.kafein.interactivenpcs.condition;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

public final class ConditionFactoryMap {
    private static final ConditionFactoryMap DEFAULT;

    static {
        Map<String, ConditionFactory<? extends Condition>> map = ImmutableMap.of(
                "EXP", ExpCondition.getFactory(),
                "ITEM", ItemCondition.getFactory(),
                "MONEY", MoneyCondition.getFactory()
        );
        DEFAULT = new ConditionFactoryMap(map);
    }

    private final Cache<String, ConditionFactory<? extends Condition>> cache;

    public ConditionFactoryMap(@NotNull Map<String, ConditionFactory<? extends Condition>> map) {
        this.cache = CacheBuilder.newBuilder()
                .build();
        this.cache.putAll(map);
    }

    public ConditionFactoryMap(@NotNull Cache<String, ConditionFactory<? extends Condition>> cache) {
        this.cache = cache;
    }

    public Cache<String, ConditionFactory<? extends Condition>> getCache() {
        return this.cache;
    }

    @SuppressWarnings("unchecked")
    public <T extends Condition> ConditionFactory<T> get(String type) {
        return (ConditionFactory<T>) this.cache.getIfPresent(type.toUpperCase(Locale.ENGLISH));
    }

    public <T extends Condition> void put(String type, ConditionFactory<T> factory) {
        this.cache.put(type.toUpperCase(Locale.ENGLISH), factory);
    }

    public <T extends Condition> void remove(String type) {
        this.cache.invalidate(type.toUpperCase(Locale.ENGLISH));
    }

    public static ConditionFactoryMap getDefault() {
        return DEFAULT;
    }
}
