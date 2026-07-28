package com.sky0427_coduck.listeners.mob;

import com.sky0427_coduck.helper.PiglinTradeTracker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PiglinBarterListener implements Listener {

    private final JavaPlugin plugin;

    public PiglinBarterListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBarter(PiglinBarterEvent event) {

        UUID playerId = PiglinTradeTracker.PIGLIN_TRADERS.remove(
                event.getEntity().getUniqueId()
        );

        if (playerId == null) return;

        Player player = Bukkit.getPlayer(playerId);
        if (player == null || !player.isOnline()) return;

        // 거래 100% 실패
        event.getOutcome().clear();

        player.sendMessage(Component.text(
                "피글린이 거래를 거부했습니다!", //TODO: 다이아몬드로 거래 활성화
                NamedTextColor.RED
        ));

        // 30% 확률로 1초 뒤 현재 플레이어 위치에 TNT 생성
        if (ThreadLocalRandom.current().nextDouble() < 0.3) {

            player.sendMessage(Component.text(
                    "뭔가 불길한 예감이 듭니다... 이번엔 30%입니다!",
                    NamedTextColor.DARK_RED
            ));

            Bukkit.getScheduler().runTaskLater(plugin, () -> {

                if (!player.isOnline()) return;

                Location loc = player.getLocation();

                Bukkit.getLogger().info(
                        "[Piglin TNT] " + player.getName()
                                + " (" + loc.getBlockX()
                                + ", " + loc.getBlockY()
                                + ", " + loc.getBlockZ() + ")"
                );

                TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);
                tnt.setFuseTicks(0); // 생성 즉시 폭발

            }, 20L); // 20틱 = 1초
        }
    }
}