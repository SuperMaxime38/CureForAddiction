package fr.maxime38.cfa.listeners;

import fr.maxime38.cfa.utils.RandomBetween;
import fr.maxime38.cfa.utils.VoiceLines;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Interact implements Listener {
  @EventHandler
  public static void onInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
      ItemStack item;
      Item it;
      Random rdm;
      int luck;
      Block b = e.getClickedBlock();
      switch (b.getType()) {
        case BLACK_BED:
          explodeBed(p, b.getLocation());
          break;
        case BLUE_BED:
          explodeBed(p, b.getLocation());
          break;
        case BROWN_BED:
          explodeBed(p, b.getLocation());
          break;
        case CYAN_BED:
          explodeBed(p, b.getLocation());
          break;
        case GRAY_BED:
          explodeBed(p, b.getLocation());
          break;
        case GREEN_BED:
          explodeBed(p, b.getLocation());
          break;
        case LIGHT_BLUE_BED:
          explodeBed(p, b.getLocation());
          break;
        case LIGHT_GRAY_BED:
          explodeBed(p, b.getLocation());
          break;
        case LIME_BED:
          explodeBed(p, b.getLocation());
          break;
        case MAGENTA_BED:
          explodeBed(p, b.getLocation());
          break;
        case ORANGE_BED:
          explodeBed(p, b.getLocation());
          break;
        case PINK_BED:
          explodeBed(p, b.getLocation());
          break;
        case PURPLE_BED:
          explodeBed(p, b.getLocation());
          break;
        case RED_BED:
          explodeBed(p, b.getLocation());
          break;
        case WHITE_BED:
          explodeBed(p, b.getLocation());
          break;
        case YELLOW_BED:
          explodeBed(p, b.getLocation());
          break;
	default:
		break;
      } 
      Material mat = p.getInventory().getItemInMainHand().getType();
      switch (mat) {
        case FLINT_AND_STEEL:
          item = p.getInventory().getItemInMainHand();
          it = p.getWorld().dropItemNaturally(p.getLocation(), item);
          it.setVelocity(p.getLocation().getDirection().multiply(RandomBetween.randomDouble(0.2D, 1.0D)).add(new Vector(0.0D, 0.3D, 0.0D)));
          p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
          rdm = new Random();
          luck = rdm.nextInt(100);
          if (luck < 25)
            p.sendMessage(VoiceLines.getFlintLine()); 
          break;
		default:
			break;
      } 
    } 
  }
  
  public static void explodeBed(Player p, Location loc) {
    if (loc.getWorld().getName() != "world_nether") {
      Random rdm = new Random();
      int luck = rdm.nextInt(100);
      if (luck < 20) {
        loc.getBlock().setType(Material.AIR);
        loc.getWorld().createExplosion(loc, 5.0F, true);
        luck = rdm.nextInt(100);
        if (luck < 25)
          p.sendMessage(VoiceLines.getBedLine()); 
      } 
    } 
  }
}
