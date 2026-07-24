package com.sky;

import commands.ImpossibleCommand;
import listeners.nether.NetherPortalListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltraDifficultyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        registerCommand("impossible", new ImpossibleCommand());
        getServer().getPluginManager().registerEvents(
                new NetherPortalListener(),
                this
        );
        getLogger().info("UltraDifficultyPlugin 시작!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
