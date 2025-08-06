package fr.maxime38.cfa.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.utils.VoiceLines;

public class Move implements Listener {
	
	static Main main;
	
	public Move(Main main) {
		Move.main = main;
	}
	
	
  @EventHandler
  public static void onChunkLoad(ChunkLoadEvent e) {}
  
  @EventHandler
  public static void onChunkUnload(ChunkUnloadEvent e) {}
  
  @EventHandler
  public static void onMove(PlayerMoveEvent e) {
    Random rdm = new Random();
    int luck = rdm.nextInt(100000);
    Player p = e.getPlayer();
    if (luck < 3) {
      int x = rdm.nextInt(6) - 3;
      int z = rdm.nextInt(6) - 3;
      Location loc = new Location(p.getWorld(), p.getLocation().getX() + x, (p.getWorld().getHighestBlockAt(x, z).getY() + 1), p.getLocation().getZ() + z);
      p.getWorld().strikeLightning(loc);
      p.sendMessage(VoiceLines.getStrikeLine());
      return;
    }
    if(luck > 99996) { // 4% ou 3% chais pas
    	p.setSneaking(true);
    	for(int i = 0; i < 9; i++) {
    		ItemStack it = p.getInventory().getItem(i);
    		if(it == null) continue;
    		if(it.getType().equals(Material.AIR)) continue;
    		p.getWorld().dropItemNaturally(p.getLocation(), it);
    		
    		p.getInventory().setItem(i, new ItemStack(Material.AIR));
    		
    		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 3));
    		
    		p.getLocation().setPitch(45);
		}
    	
    	p.setVelocity(p.getVelocity().add(p.getLocation().getDirection().multiply(0.5)));
    	new BukkitRunnable() {

			@Override
			public void run() {
				p.setSneaking(false);
	    		p.getLocation().setPitch(0);
			}
	    	
    	}.runTaskLater(main, 10);
    	
    	p.sendMessage(VoiceLines.getStumbleLine());
    	
		return;
    }
  }
}
