package dev.kafein.npcinteractions.objective.condition;

import ninja.leaping.configurate.ConfigurationNode;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ConditionFactory {
    private ConditionFactory() {}

    public static Condition createExpCondition(int exp) {
        return new ExpCondition(exp);
    }

    public static Condition createItemCondition(ItemStack item) {
        return new ItemCondition(item);
    }

    public static Condition createMoneyCondition(double money) {
        return new MoneyCondition(money);
    }

    /*public static Condition createCondition(@NotNull ConfigurationNode node) {

    }*/
}
