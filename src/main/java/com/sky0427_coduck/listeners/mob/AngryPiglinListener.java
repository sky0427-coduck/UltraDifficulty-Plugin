package com.sky0427_coduck.listeners.mob;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.entity.CraftPiglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class AngryPiglinListener implements Listener { // 1. implements Listener 추가

    @EventHandler
    public void onPiglinSpawn(EntitySpawnEvent event) {

        if (!(event.getEntity() instanceof org.bukkit.entity.Piglin bukkitPiglin)) return;

        // 3. Bukkit 엔티티를 NMS 엔티티로 변환
        Piglin nmsPiglin = ((CraftPiglin) bukkitPiglin).getHandle();

        nmsPiglin.targetSelector.removeAllGoals(goal -> true);


        nmsPiglin.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
                nmsPiglin,
                Player.class,
                true // 시야 내에 있는 플레이어 타겟팅 (벽 뒤는 무시)
        ));

        nmsPiglin.getBrain().eraseMemory(net.minecraft.world.entity.ai.memory.MemoryModuleType.UNIVERSAL_ANGER);
    }

}
