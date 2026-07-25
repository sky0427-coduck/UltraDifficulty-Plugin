package listeners;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import managers.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.ThreadLocalRandom;

public class RespawnListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (!GameManager.isRunning()) {
            return;
        }

        // 블록 우클릭 상호작용만 감지
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;

        Material blockType = event.getClickedBlock().getType();
        Player player = event.getPlayer();

        // 클릭한 블록이 침대이거나 리스폰 앵커인 경우
        if (Tag.BEDS.isTagged(blockType) || blockType == Material.RESPAWN_ANCHOR) {

            event.setUseInteractedBlock(org.bukkit.event.Event.Result.ALLOW);

            // 30% 확률로 실패
            if (ThreadLocalRandom.current().nextDouble() < 0.3) {

                // 우클릭 차단
                event.setCancelled(true);

                player.sendMessage(
                        Component.text(
                                player.getName() + "님이 리스폰 설정에 실패했습니다! (30% 확률)",
                                NamedTextColor.RED
                        )
                );

                // 고정 피해 계산 및 적용
                double currentHealth = player.getHealth();
                double newHealth = Math.max(0.0, currentHealth - 20.0);
                player.setHealth(newHealth);

                // [중요] 체력이 0 이하가 되어 죽는 경우, 서버에 사망 트리거를 발동시켜 싱크 에러를 방지
                if (newHealth <= 0.0) {
                    player.damage(1.0);
                }
            }
        }
    }

    // 30% 실패로 상호작용이 취소 ->  시스템 스폰 지점 등록도 함께 취소하여 전 스폰 유지
    @EventHandler
    public void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        if (!GameManager.isRunning()) return;

        if (event.getCause() == PlayerSetSpawnEvent.Cause.BED ||
                event.getCause() == PlayerSetSpawnEvent.Cause.RESPAWN_ANCHOR) {

        }
    }
}
