package com.sky0427_coduck.listeners;

import com.sky0427_coduck.UltraDifficultyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class EyesOfEnderListener implements Listener {

    @EventHandler
    public void onEnderEyeInsert(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null || clickedBlock.getType() != Material.END_PORTAL_FRAME) return;


        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();


        if (itemInHand.getType() != Material.ENDER_EYE) return;


        if (clickedBlock.getBlockData() instanceof EndPortalFrame frameData) {
            if (frameData.hasEye()) return;
        }

        // 50% 확률 계산
        if (ThreadLocalRandom.current().nextDouble() < 0.50) {
            event.setCancelled(true);

            // 사용된 엔더의 눈 1개 소멸 처리
            itemInHand.setAmount(itemInHand.getAmount() - 1);

            org.bukkit.Location targetLocation = clickedBlock.getLocation().add(0.5, 0.5, 0.5);
            World world = clickedBlock.getWorld();

            // 소멸 파티클
            world.playSound(targetLocation, Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 2.6F);
            world.spawnParticle(Particle.SMOKE, targetLocation, 8, 0.1, 0.1, 0.1, 0.05);



            if (UltraDifficultyPlugin.shownMessages.add("ENDER_EYES")) {
                player.sendMessage(
                        Component.text(
                                player.getName() + "손에 참기름을 바르셨나요? 엔더의 눈이 그대로 날아가버렸네요!",
                                NamedTextColor.RED
                        )
                );
            }
        }
    }
}
