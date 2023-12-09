package dev.kafein.interactivenpcs.condition;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

final class ItemCondition implements Condition {
    private static final ConditionFactory<ItemCondition> FACTORY = new Factory();

    private final ItemStack item;

    ItemCondition(ItemStack item) {
        this.item = item;
    }

    @Override
    public void apply(Player player) {
        player.getInventory().removeItem(this.item);
    }

    @Override
    public @NotNull String getType() {
        return "ITEM";
    }

    @Override
    public boolean test(Player player) {
        return player.getInventory().containsAtLeast(this.item, this.item.getAmount());
    }

    public static ConditionFactory<ItemCondition> getFactory() {
        return FACTORY;
    }

    private static final class Factory implements ConditionFactory<ItemCondition> {
        private Factory() {
        }

        @Override
        public ItemCondition create(@NotNull String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ItemCondition create(@NotNull ConfigurationNode node) throws ObjectMappingException {
            ItemStack item = node.getValue(TypeToken.of(ItemStack.class));
            return new ItemCondition(item);
        }
    }
}
