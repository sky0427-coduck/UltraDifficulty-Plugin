package com.sky.listeners.nether.mob;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;

public class PiglinBarterListener implements Listener {

    @EventHandler
    public void onBarter(PiglinBarterEvent event) {

        event.getOutcome().clear();
    }
}