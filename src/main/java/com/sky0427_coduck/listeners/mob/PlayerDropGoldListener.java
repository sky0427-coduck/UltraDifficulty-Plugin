package com.sky0427_coduck.listeners.mob;

import com.sky0427_coduck.helper.PiglinTradeTracker;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropGoldListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        if (event.getItemDrop().getItemStack().getType() != Material.GOLD_INGOT) return;

        PiglinTradeTracker.ITEM_OWNERS.put(
                event.getItemDrop().getUniqueId(),
                event.getPlayer().getUniqueId()
        );
    }
}