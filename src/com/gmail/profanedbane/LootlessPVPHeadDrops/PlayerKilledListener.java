package com.gmail.profanedbane.LootlessPVPHeadDrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

// Identify PVP player deaths and try to drop a head on the killed player
public class PlayerKilledListener implements Listener {
    private final LootlessPVPHeadDrops plugin;

    PlayerKilledListener(LootlessPVPHeadDrops plugin) { this.plugin = plugin; }

    // Players getting killed
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    private void playerKilled(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity killer = player.getKiller();

        if(killer instanceof Player) {
            // Setup skull to be dropped
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwningPlayer(player);
            meta.setDisplayName(player.getDisplayName() + "'s Head");
            ArrayList<String> skullLore = new ArrayList<String>();
            skullLore.add("Killed by " + killer.getName());
            meta.setLore(skullLore);
            skull.setItemMeta(meta);

            // Drop skull at dead player
            Location playerLoc = player.getLocation();
            playerLoc.getWorld().dropItem(playerLoc, skull);
        }
    }
}
