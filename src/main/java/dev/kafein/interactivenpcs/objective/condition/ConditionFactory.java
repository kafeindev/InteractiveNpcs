package dev.kafein.interactivenpcs.objective.condition;

import org.bukkit.inventory.ItemStack;

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
