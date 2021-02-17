package com.gmail.profanedbane.LootlessPVPHeadDrops;

import org.bukkit.plugin.java.JavaPlugin;

public class LootlessPVPHeadDrops extends JavaPlugin {
    @Override
    public void onEnable(){
        // Setup listener
        getServer().getPluginManager().registerEvents( new PlayerKilledListener(this), this);
    }
}
