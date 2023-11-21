package dev.kafein.interactivenpcs.objective.condition;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemCondition implements Condition {
    private final ItemStack item;

    public ItemCondition(ItemStack item) {
        this.item = item;
    }

    @Override
    public void apply(Player player) {
        player.getInventory().removeItem(this.item);
    }

    @Override
    public boolean test(Player player) {
        return player.getInventory().containsAtLeast(this.item, this.item.getAmount());
    }
}
