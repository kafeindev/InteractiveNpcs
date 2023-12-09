package dev.kafein.interactivenpcs.condition;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.jetbrains.annotations.NotNull;

public interface ConditionFactory<T extends Condition> {
    T create(@NotNull String value);

    T create(@NotNull ConfigurationNode node) throws ObjectMappingException;
}
