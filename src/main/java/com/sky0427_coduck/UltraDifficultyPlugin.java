package com.sky0427_coduck;

import com.sky0427_coduck.commands.ImpossibleCommand;
import com.sky0427_coduck.listeners.RespawnListener;
import com.sky0427_coduck.listeners.WaterEvaporateListener;
import com.sky0427_coduck.listeners.mob.*;
import com.sky0427_coduck.listeners.nether.NetherPortalListener;
import com.sky0427_coduck.task.NetherFireScheduler;
import org.bukkit.plugin.java.JavaPlugin;
import com.sky0427_coduck.task.AngryPiglinScheduler;

import java.util.HashSet;
import java.util.Set;

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
        var sp = getServer().getPluginManager(); //제미나이 꼼수 gg
        sp.registerEvents(new NetherPortalListener(), this);
        sp.registerEvents(new RespawnListener(), this);
        sp.registerEvents(new WaterEvaporateListener(), this);
        sp.registerEvents(new CreeperShieldBreakListener(), this);
        sp.registerEvents(new PiglinBarterListener(this), this);
        sp.registerEvents(new MobKillListener(), this);
        sp.registerEvents(new PlayerDropGoldListener(), this);
        sp.registerEvents(new PiglinPickupListener(), this);
    }

    private void startSchedulers() {
        new AngryPiglinScheduler().runTaskTimer(this, 0L, 2L); // 피글린 타깃 갱신 스케줄러
        new NetherFireScheduler().runTaskTimer(this, 0L, 5L);
    }

    public static final Set<String> shownMessages = new HashSet<>(); // 서버메시지 출력 기준


}
