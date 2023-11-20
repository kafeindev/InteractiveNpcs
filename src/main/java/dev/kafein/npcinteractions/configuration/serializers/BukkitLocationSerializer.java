package dev.kafein.npcinteractions.configuration.serializers;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.bukkit.Location;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class BukkitLocationSerializer implements TypeSerializer<Location> {
    private static final BukkitLocationSerializer INSTANCE = new BukkitLocationSerializer();

    private BukkitLocationSerializer() {}

    public static BukkitLocationSerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public @Nullable Location deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (!value.isEmpty()) {
            return null;
        }

        String[] split = value.getString().split(",");
        if (split.length < 5) {
            throw new ObjectMappingException("Invalid location format");
        }

        return new Location(null,
                Double.parseDouble(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Float.parseFloat(split[3]),
                Float.parseFloat(split[4])
        );
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Location obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        if (obj == null) {
            value.setValue("");
            return;
        }

        value.setValue(String.format("%s,%s,%s,%s,%s,%s",
                obj.getX(),
                obj.getY(),
                obj.getZ(),
                obj.getYaw(),
                obj.getPitch(),
                obj.getWorld().getName()
        ));
    }
}
