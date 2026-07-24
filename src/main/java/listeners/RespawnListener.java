package listeners;

import managers.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.concurrent.ThreadLocalRandom;

public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        if (!GameManager.isRunning()) {
            return;
        }



        // 10% 실패
        if (ThreadLocalRandom.current().nextDouble() >= 0.1) {

            event.isBedSpawn();

            Player player = event.getPlayer();



            player.sendMessage(
                    Component.text(
                            "리스폰 설정하지 못했습니다! (10% 확률)",
                            NamedTextColor.RED
                    )
            );

            player.damage(5.0);
        }
    }

}
