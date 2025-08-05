package fr.maxime38.cfa.boss;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class BossManager implements Listener {
  public static int gameState = 0;
  
  @EventHandler
  public static void takePortal(PlayerPortalEvent e) {
    if (!e.getTo().getWorld().equals(Bukkit.getWorlds().get(2)))
      return; 
    if (gameState == 0)
      gameState = 1; 
  }
}
