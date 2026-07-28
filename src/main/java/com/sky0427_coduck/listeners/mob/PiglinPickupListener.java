package com.sky0427_coduck.listeners.mob;

import com.sky0427_coduck.helper.PiglinTradeTracker;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.UUID;

public class PiglinPickupListener implements Listener {

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {

        if (!(event.getEntity() instanceof Piglin piglin)) return;

        UUID playerId = PiglinTradeTracker.ITEM_OWNERS.remove(
                event.getItem().getUniqueId()
        );

        if (playerId == null) return;

        PiglinTradeTracker.PIGLIN_TRADERS.put(
                piglin.getUniqueId(),
                playerId
        );
    }
}