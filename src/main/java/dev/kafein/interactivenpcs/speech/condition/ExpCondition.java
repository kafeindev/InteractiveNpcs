package dev.kafein.interactivenpcs.speech.condition;

import dev.kafein.interactivenpcs.utils.Experience;
import org.bukkit.entity.Player;

public final class ExpCondition implements Condition {
    private final int exp;

    public ExpCondition(int exp) {
        this.exp = exp;
    }

    @Override
    public void apply(Player player) {
        int currentExp = Experience.getExp(player);
        player.setExp(currentExp - this.exp);
    }

    @Override
    public boolean test(Player player) {
        return Experience.getExp(player) >= this.exp;
    }
}
