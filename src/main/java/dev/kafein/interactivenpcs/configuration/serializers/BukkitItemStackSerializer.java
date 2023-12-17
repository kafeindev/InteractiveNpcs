package dev.kafein.interactivenpcs.configuration.serializers;

import com.google.common.reflect.TypeToken;
import dev.kafein.interactivenpcs.utils.LegacyTextSerializer;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public final class BukkitItemStackSerializer implements TypeSerializer<ItemStack> {
    private static final BukkitItemStackSerializer INSTANCE = new BukkitItemStackSerializer();

    private BukkitItemStackSerializer() {
    }

    public static BukkitItemStackSerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public @Nullable ItemStack deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (!value.isMap()) {
            throw new ObjectMappingException("Expected a map");
        }

        Material material = Material.valueOf(value.getNode("material").getString("AIR"));
        int amount = value.getNode("amount").getInt(1);
        short damage = (short) value.getNode("damage").getInt(0);

        ItemStack itemStack = new ItemStack(material, amount, damage);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (!value.getNode("name").isEmpty()) {
            String name = value.getNode("name").getString();
            itemMeta.setDisplayName(LegacyTextSerializer.deserialize(name));
        }
        if (!value.getNode("lore").isEmpty()) {
            List<String> lore = value.getNode("lore").getList(TypeToken.of(String.class));
            itemMeta.setLore(LegacyTextSerializer.deserialize(lore));
        }
        if (!value.getNode("enchantments").isEmpty()) {
            List<String> enchantments = value.getNode("enchantments").getList(TypeToken.of(String.class));
            for (String enchantment : enchantments) {
                String[] split = enchantment.split(":");
                itemMeta.addEnchant(Enchantment.getByName(split[0]), Integer.parseInt(split[1]), true);
            }
        }
        if (!value.getNode("flags").isEmpty()) {
            List<String> flags = value.getNode("flags").getList(TypeToken.of(String.class));
            for (String flag : flags) {
                itemMeta.addItemFlags(ItemFlag.valueOf(flag));
            }
        }
        if (!value.getNode("custom-model-data").isEmpty()) {
            itemMeta.setCustomModelData(value.getNode("custom-model-data").getInt());
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable ItemStack obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        throw new UnsupportedOperationException();
    }
}
