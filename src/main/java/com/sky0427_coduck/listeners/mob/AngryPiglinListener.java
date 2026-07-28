package com.sky0427_coduck.listeners.mob;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.entity.CraftPiglin; // 26.1.2 버전에 최적화된 패키지 경로
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class AngryPiglinListener implements Listener { // 1. implements Listener 추가

    @EventHandler
    public void onPiglinSpawn(EntitySpawnEvent event) {
        // 2. BukkitPiglin 대신 버킷 공식 클래스명인 org.bukkit.entity.Piglin 사용
        if (!(event.getEntity() instanceof org.bukkit.entity.Piglin bukkitPiglin)) return;

        // 3. Bukkit 엔티티를 NMS 엔티티로 변환 (정확한 패키지 캐스팅 적용)
        Piglin nmsPiglin = ((CraftPiglin) bukkitPiglin).getHandle();

        // 4. 기존의 타겟 셀렉터(금 갑옷을 체크하는 AI 등)를 완전히 초기화
        nmsPiglin.targetSelector.removeAllGoals(goal -> true);

        // 5. 금 갑옷 착용 여부 조건을 완전히 무시하고 '모든 플레이어'를 타겟으로 잡는 새로운 AI Goal 주입
        // NearestAttackableTargetGoal<>(엔티티, 타겟클래스, checkVisibility)
        nmsPiglin.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
                nmsPiglin,
                Player.class,
                true // 시야 내에 있는 플레이어 타겟팅 (벽 뒤는 무시, 무조건 타겟팅은 false)
        ));

        // 6. (선택사항) 필요 시 피글린 브레인의 기억(Memory)을 지우거나 적대 상태로 강제 전환
        // nmsPiglin.getBrain().eraseMemory(net.minecraft.world.entity.ai.memory.MemoryModuleType.UNIVERSAL_ANGER);
    }

}
