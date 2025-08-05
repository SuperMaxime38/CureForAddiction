package fr.maxime38.cfa.listeners;

import fr.maxime38.cfa.utils.VoiceLines;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.util.Vector;

public class PlaceBlocks implements Listener {
  @EventHandler
  public static void placeBlock(BlockPlaceEvent e) {
    Block b = e.getBlock();
    Player p = e.getPlayer();
    switch (b.getType()) {
      case SAND:
        fallingSand(b, p);
        break;
      case GRAVEL:
        fallingSand(b, p);
        break;
      case RED_SAND:
        fallingSand(b, p);
        break;
	default:
		break;
    } 
  }
  
  @EventHandler
  public static void water(PlayerBucketEmptyEvent e) {
    Random rdm = new Random();
    if (e.getBucket() == Material.WATER_BUCKET) {
      int luck = rdm.nextInt(100);
      if (luck < 1) {
        e.setCancelled(true);
        int bucket = e.getPlayer().getInventory().first(Material.WATER_BUCKET);
        e.getPlayer().getInventory().getItem(bucket).setType(Material.BUCKET);
        e.getBlock().setType(Material.LAVA);
        luck = rdm.nextInt(100);
        if (luck < 90)
          e.getPlayer().sendMessage(VoiceLines.getWaterPlaceLine()); 
        return;
      } 
      luck = rdm.nextInt(1000);
      if (luck < 5) {
        e.setCancelled(true);
        int bucket = e.getPlayer().getInventory().first(Material.WATER_BUCKET);
        e.getPlayer().getInventory().getItem(bucket).setType(Material.BUCKET);
        e.getPlayer().sendMessage(VoiceLines.getCanceledWaterLine());
      } 
    } 
    if (e.getBucket() == Material.LAVA_BUCKET) {
      int luck = rdm.nextInt(100);
      if (luck < 1) {
        e.setCancelled(true);
        int bucket = e.getPlayer().getInventory().first(Material.LAVA_BUCKET);
        e.getPlayer().getInventory().getItem(bucket).setType(Material.BUCKET);
        e.getBlock().setType(Material.OBSIDIAN);
        e.getPlayer().sendMessage(VoiceLines.getColdenLava());
        return;
      } 
      if (luck > 98) {
        e.setCancelled(true);
        int bucket = e.getPlayer().getInventory().first(Material.LAVA_BUCKET);
        e.getPlayer().getInventory().getItem(bucket).setType(Material.BUCKET);
        e.getBlock().setType(Material.STONE);
        e.getPlayer().sendMessage(VoiceLines.getStonedLava());
        return;
      } 
    } 
  }
  
  @SuppressWarnings("deprecation")
public static void fallingSand(Block b, Player p) {
    Random rdm = new Random();
    int luck = rdm.nextInt(100);
    if (luck < 50) {
      FallingBlock f;
      if (b.getType() == Material.SAND) {
        f = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5D, 0.5D, 0.5D), Material.SAND, (byte)0);
      } else if (b.getType() == Material.RED_SAND) {
        f = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5D, 0.5D, 0.5D), Material.RED_SAND, (byte)0);
      } else {
        f = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5D, 0.5D, 0.5D), Material.GRAVEL, (byte)0);
      } 
      b.setType(Material.AIR);
      f.setVelocity(new Vector(0, 5, 0));
      luck = rdm.nextInt(100);
      if (luck < 25)
        p.sendMessage(VoiceLines.getSandFallingLine()); 
    } 
  }
}
