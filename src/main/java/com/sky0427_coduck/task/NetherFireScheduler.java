package com.sky0427_coduck.task;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NetherFireScheduler extends BukkitRunnable {

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (player.getWorld().getEnvironment() != World.Environment.NETHER)
                continue;

            Material block = player.getLocation()
                    .subtract(0, 1, 0)
                    .getBlock()
                    .getType();

            if (block == Material.NETHERRACK) {
                player.setFireTicks(40); // 2초 동안 불
            }
            else {
                player.setFireTicks(0);
            }
        }
    }
}