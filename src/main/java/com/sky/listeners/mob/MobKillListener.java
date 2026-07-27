package com.sky.listeners.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class MobKillListener implements Listener {

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        Player killer = entity.getKiller();
        if (killer == null) {
            return;
        }


        if (isTargetMob(entity)) {
            Location loc = entity.getLocation();
            World world = loc.getWorld();
            if (world == null) return;

            // 아기 좀비 구별용 플래그
            boolean isBabyZombie = entity instanceof Zombie && !((Zombie) entity).isAdult();


            String worldName = world.getName();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();


            if (ThreadLocalRandom.current().nextDouble() < 0.1) {


                Entity clone = world.spawnEntity(loc, entity.getType());

                if (isBabyZombie && clone instanceof Zombie) {
                    ((Zombie) clone).setBaby();
                }
            }

        }
    }

    private boolean isTargetMob(LivingEntity entity) {
        EntityType type = entity.getType();
        return type == EntityType.ZOMBIE ||
                type == EntityType.ZOMBIE_VILLAGER ||
                type == EntityType.SKELETON ||
                type == EntityType.CREEPER ||
                type == EntityType.WITHER_SKELETON ||
                type == EntityType.SPIDER ||
                type == EntityType.CAVE_SPIDER;
    }
}