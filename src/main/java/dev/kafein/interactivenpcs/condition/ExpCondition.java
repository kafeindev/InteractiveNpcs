package dev.kafein.interactivenpcs.condition;

import dev.kafein.interactivenpcs.utils.Experience;
import ninja.leaping.configurate.ConfigurationNode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

final class ExpCondition implements Condition {
    private static final ConditionFactory<ExpCondition> FACTORY = new Factory();

    private final int exp;

    ExpCondition(int exp) {
        this.exp = exp;
    }

    @Override
    public void apply(Player player) {
        int currentExp = Experience.getExp(player);
        player.setExp(currentExp - this.exp);
    }

    @Override
    public @NotNull String getType() {
        return "EXP";
    }

    @Override
    public boolean test(Player player) {
        return Experience.getExp(player) >= this.exp;
    }

    public static ConditionFactory<ExpCondition> getFactory() {
        return FACTORY;
    }

    private static final class Factory implements ConditionFactory<ExpCondition> {
        private Factory() {
        }

        @Override
        public ExpCondition create(@NotNull String value) {
            int exp = Integer.parseInt(value);
            return new ExpCondition(exp);
        }

        @Override
        public ExpCondition create(@NotNull ConfigurationNode node) {
            int exp = node.getInt();
            return new ExpCondition(exp);
        }
    }
}
