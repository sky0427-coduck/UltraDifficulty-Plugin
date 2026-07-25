package com.sky.task;

import com.sky.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AngryPiglin extends BukkitRunnable {

    @Override
    public void run() {

        if (!GameManager.isRunning()) {
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!isWearingGoldArmor(player)) {
                continue;
            }

            for (Entity entity : player.getNearbyEntities(16, 16, 16)) {

                if (entity instanceof Piglin piglin) {
                    piglin.setTarget(player);
                }
            }
        }
    }

    private boolean isWearingGoldArmor(Player player) {
        return isGold(player.getInventory().getHelmet())
                || isGold(player.getInventory().getChestplate())
                || isGold(player.getInventory().getLeggings())
                || isGold(player.getInventory().getBoots());
    }

    private boolean isGold(ItemStack item) {

        if (item == null) {
            return false;
        }

        return switch (item.getType()) {
            case GOLDEN_HELMET,
                 GOLDEN_CHESTPLATE,
                 GOLDEN_LEGGINGS,
                 GOLDEN_BOOTS -> true;
            default -> false;
        };
    }
}