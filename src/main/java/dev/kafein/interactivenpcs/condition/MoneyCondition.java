package dev.kafein.interactivenpcs.condition;

import dev.kafein.interactivenpcs.compatibility.implementation.VaultCompatibility;
import ninja.leaping.configurate.ConfigurationNode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

final class MoneyCondition implements Condition {
    private static final ConditionFactory<MoneyCondition> FACTORY = new Factory();

    private final double money;

    MoneyCondition(double money) {
        this.money = money;
    }

    @Override
    public void apply(Player player) {
        VaultCompatibility.getEconomy().withdrawPlayer(player, this.money);
    }

    @Override
    public @NotNull String getType() {
        return "MONEY";
    }

    @Override
    public boolean test(Player player) {
        return VaultCompatibility.getEconomy().has(player, this.money);
    }

    public static ConditionFactory<MoneyCondition> getFactory() {
        return FACTORY;
    }

    private static final class Factory implements ConditionFactory<MoneyCondition> {
        private Factory() {
        }

        @Override
        public MoneyCondition create(@NotNull String value) {
            double money = Double.parseDouble(value);
            return new MoneyCondition(money);
        }

        @Override
        public MoneyCondition create(@NotNull ConfigurationNode node) {
            double money = node.getDouble();
            return new MoneyCondition(money);
        }
    }
}
