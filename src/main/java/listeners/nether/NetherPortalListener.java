package listeners.nether;

import managers.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.concurrent.ThreadLocalRandom;

public class NetherPortalListener implements Listener {

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {

        if (!GameManager.isRunning()) {
            return;
        }

        World.Environment from = event.getFrom().getWorld().getEnvironment();
        World.Environment to = event.getTo().getWorld().getEnvironment();

        // 오버월드 < - > 네더 이동만 처리
        boolean overworldToNether =
                from == World.Environment.NORMAL &&
                        to == World.Environment.NETHER;

        boolean netherToOverworld =
                from == World.Environment.NETHER &&
                        to == World.Environment.NORMAL;

        if (!overworldToNether && !netherToOverworld) {
            return;
        }

        // 50% 실패
        if (ThreadLocalRandom.current().nextDouble() >= 0.5) {

            event.setCancelled(true);

            Player player = event.getPlayer();

            String target =
                    overworldToNether ? "네더월드" : "오버월드";

            player.sendActionBar(
                    Component.text(
                            target + "로 넘어가지 못했습니다! 다시 시도하세요. (50% 확률)",
                            NamedTextColor.RED
                    )
            );

            player.damage(5.0);
        }
    }
}