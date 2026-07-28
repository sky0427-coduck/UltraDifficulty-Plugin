package com.sky0427_coduck.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Deprecated

public class AngryPiglinScheduler extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            for (var entity : player.getNearbyEntities(20, 20, 20)) {

                if (entity instanceof Piglin piglin) {

                    piglin.setTarget(player);
                }
            }
        }
    }
}