package com.sky.listeners;

import com.sky.managers.GameManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.concurrent.ThreadLocalRandom;

public class WaterEvaporateListener implements Listener {

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        if (!GameManager.isRunning()) {
            return;
        }

        // 사용한 양동이가 물양동이인지 확인
        if (event.getBucket() != Material.WATER_BUCKET) {
            return;
        }

        Player player = event.getPlayer();
        World world = player.getWorld();

        // 오버월드(NORMAL) 또는 엔드월드(THE_END)인지 확인
        if (world.getEnvironment() == World.Environment.NORMAL ||
                world.getEnvironment() == World.Environment.THE_END) {

            // 40% 확률로 증발
            if (ThreadLocalRandom.current().nextDouble() < 0.4) {


                event.setCancelled(true);
                player.sendMessage(
                        Component.text("물이 증발됐어요! ", NamedTextColor.AQUA)
                                .append(Component.text("지구온난화 탓인가봐요..", NamedTextColor.GRAY))
                );

                // 물양동이를 빈 양동이로 교체 (서바이벌 모드 전용)
                if (player.getGameMode() == org.bukkit.GameMode.SURVIVAL) {
                    // 메인 손이나 왼손 중 물양동이를 든 손의 아이템을 변경
                    if (event.getHand() == org.bukkit.inventory.EquipmentSlot.HAND) {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.BUCKET));
                    } else {
                        player.getInventory().setItemInOffHand(new ItemStack(Material.BUCKET));
                    }
                }

                // 3. 물이 쏟아지려 했던 블록 위치 가져오기
                Block clickedBlock = event.getBlockClicked();
                var blockFace = event.getBlockFace();
                // 클릭한 블록의 면 방향으로 한 칸 앞(실제 물이 생길 좌표) 계산
                var targetLocation = clickedBlock.getRelative(blockFace).getLocation().add(0.5, 0.2, 0.5);


                world.playSound(targetLocation, Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 2.6F);
                world.spawnParticle(Particle.SMOKE, targetLocation, 8, 0.1, 0.1, 0.1, 0.05);
            }
        }
    }

}
