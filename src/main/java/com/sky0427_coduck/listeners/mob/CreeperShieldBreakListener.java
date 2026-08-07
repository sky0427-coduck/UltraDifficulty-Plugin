package com.sky0427_coduck.listeners.mob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class CreeperShieldBreakListener implements Listener {

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {

        if (!(event.getEntity() instanceof Creeper creeper)) {
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!isBlockingWithShield(player)) {
                continue;
            }

            if (ThreadLocalRandom.current().nextDouble() < 0.1) {
                breakShield(player);
                player.sendActionBar(
                        Component.text(
                                player.getName() + "크리퍼가 방패를 깨버렸습니다! 더 튼튼한 방패로 맞추시죠? (없음)",
                                NamedTextColor.WHITE
                        )
                );
            }
        }
    }


    private boolean isBlockingWithShield(Player player) {

        if (!player.isBlocking()) {
            return false;
        }

        return player.getInventory().getItemInMainHand().getType() == Material.SHIELD
                || player.getInventory().getItemInOffHand().getType() == Material.SHIELD;
    }


    private void breakShield(Player player) {

        ItemStack shield;

        if (player.getInventory().getItemInMainHand().getType() == Material.SHIELD) {
            shield = player.getInventory().getItemInMainHand();
        }
        else if (player.getInventory().getItemInOffHand().getType() == Material.SHIELD) {
            shield = player.getInventory().getItemInOffHand();
        }
        else {
            return;
        }

        shield.setAmount(0);
    }
}