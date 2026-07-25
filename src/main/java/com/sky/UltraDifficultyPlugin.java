package com.sky;

import com.sky.commands.ImpossibleCommand;
import com.sky.listeners.RespawnListener;
import com.sky.listeners.WaterEvaporateListener;
import com.sky.listeners.nether.NetherPortalListener;
import org.bukkit.plugin.java.JavaPlugin;
import com.sky.task.AngryPiglin;

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
        new AngryPiglin().runTaskTimer(this, 0L, 2L);
    }


}
