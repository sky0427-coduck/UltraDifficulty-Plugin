package com.sky;

import commands.ImpossibleCommand;
import listeners.RespawnListener;
import listeners.WaterEvaporateListener;
import listeners.nether.NetherPortalListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltraDifficultyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        registerCommands();

        registerListeners();

        startSchedulers();

        getLogger().info("UltraDifficultyPlugin 시작!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //-----------------------------------------------------------------------------------------------

    private void registerCommands() {
        registerCommand("impossible", new ImpossibleCommand());
    }

    private void registerListeners() {
        var sp = getServer().getPluginManager(); //제미나이 꼼수 지렸다
        sp.registerEvents(new NetherPortalListener(), this);
        sp.registerEvents(new RespawnListener(), this);
        sp.registerEvents(new WaterEvaporateListener(), this);
    }

    private void startSchedulers() {
        //TODO: 기능추가
    }


}
