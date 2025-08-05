package fr.maxime38.cfa.listeners;

import fr.maxime38.cfa.utils.VoiceLines;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class Move implements Listener {
  @EventHandler
  public static void onChunkLoad(ChunkLoadEvent e) {}
  
  @EventHandler
  public static void onChunkUnload(ChunkUnloadEvent e) {}
  
  @EventHandler
  public static void onMove(PlayerMoveEvent e) {
    Random rdm = new Random();
    int luck = rdm.nextInt(100000);
    Player p = e.getPlayer();
    if (luck < 4) {
      int x = rdm.nextInt(6) - 3;
      int z = rdm.nextInt(6) - 3;
      Location loc = new Location(p.getWorld(), p.getLocation().getX() + x, (p.getWorld().getHighestBlockAt(x, z).getY() + 1), p.getLocation().getZ() + z);
      p.getWorld().strikeLightning(loc);
      p.sendMessage(VoiceLines.getStrikeLine());
    } 
  }
}
