package fr.maxime38.cfa.listeners;


import java.util.Collection;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.utils.RandomBetween;
import fr.maxime38.cfa.utils.VoiceLines;

public class BreakBlocks implements Listener {
  static Main main;
  
  public BreakBlocks(Main main) {
    BreakBlocks.main = main;
  }
  
  @EventHandler
  public static void onBreak(BlockBreakEvent e) {
    final Player p = e.getPlayer();
    Block b = e.getBlock();
    Random rdm = new Random();
    switch (b.getType()) {
      case OAK_LOG:
        summonBoltAroundPlayer(p);
		break;
      case ACACIA_LOG:
        summonBoltAroundPlayer(p);
        break;
      case BIRCH_LOG:
        summonBoltAroundPlayer(p);
		break;
      case DARK_OAK_LOG:
        summonBoltAroundPlayer(p);
		break;
      case JUNGLE_LOG:
        summonBoltAroundPlayer(p);
		break;
      case SPRUCE_LOG:
        summonBoltAroundPlayer(p);
		break;
      case STRIPPED_ACACIA_LOG:
        summonBoltAroundPlayer(p);
        break;
      case STRIPPED_BIRCH_LOG:
        summonBoltAroundPlayer(p);
		break;
      case STRIPPED_OAK_LOG:
        summonBoltAroundPlayer(p);
		break;
      case STRIPPED_DARK_OAK_LOG:
        summonBoltAroundPlayer(p);
		break;
      case STRIPPED_JUNGLE_LOG:
        summonBoltAroundPlayer(p);
		break;
      case STRIPPED_SPRUCE_LOG:
        summonBoltAroundPlayer(p);
		break;
      case COAL_ORE:
        e.setDropItems(false);
        dropBlock(p, b, Material.COBBLESTONE);
        int luck = rdm.nextInt(100);
        if (luck < 5) {
          p.sendMessage(VoiceLines.getCoalExplosionLine());
          final Location loc = p.getLocation().clone();
          (new BukkitRunnable() {
              public void run() {
                p.getWorld().createExplosion(loc, 3.5F, true);
              }
            }).runTaskLater((Plugin)main, 50L);
        }
        break;
      case IRON_ORE:
        e.setDropItems(false);
        dropBlock(p, b, Material.COAL);
        break;
      case GOLD_ORE:
        e.setDropItems(false);
        dropBlock(p, b, Material.IRON_ORE);
		break;
      case NETHER_GOLD_ORE:
        e.setDropItems(false);
        dropBlock(p, b, Material.IRON_NUGGET);
		break;
      case DIAMOND_ORE:
        e.setDropItems(false);
        dropBlock(p, b, Material.GOLD_ORE);
		break;
      case ANCIENT_DEBRIS:
        e.setDropItems(false);
        dropBlock(p, b, Material.DIAMOND);
		break;
      case STONE:
        stoneMining(b.getLocation());
        break;
      case SANDSTONE:
        stoneMining(b.getLocation());
		break;
      case GRANITE:
        stoneMining(b.getLocation());
        break;
      case ANDESITE:
        stoneMining(b.getLocation());
        break;
      case DIORITE:
        stoneMining(b.getLocation());
        break;
      case COBBLESTONE:
        stoneMining(b.getLocation());
        break;
      case POTATOES:
    	  fuckUpHarvest(p, b, Material.POTATOES);
        break;
      case WHEAT:
    	  fuckUpHarvest(p, b, Material.WHEAT);
        break;
      case CARROTS:
    	  fuckUpHarvest(p, b, Material.CARROTS);
        break;
      case BEETROOTS:
    	  fuckUpHarvest(p, b, Material.BEETROOTS);
        break;
      default:
        return;
    } 
    int luck = rdm.nextInt(100);
    if (luck < 20) {
      e.setDropItems(false);
      b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.POISONOUS_POTATO));
    } 
  }
  
  public static void summonBoltAroundPlayer(Player p) {
    Random rdm = new Random();
    int luck = rdm.nextInt(100);
    if (luck < 20) {
      Location loc = p.getLocation();
      Location strike = loc.clone();
      strike.add(-2.5D, 0.0D, -2.5D);
      int X = rdm.nextInt(5);
      int Z = rdm.nextInt(5);
      strike.add(X, 0.0D, Z);
      loc.getWorld().strikeLightning(strike);
      luck = rdm.nextInt(100);
      if (luck < 25)
        p.sendMessage(VoiceLines.getStrikeLine()); 
    } 
  }
  
  public static void dropBlock(Player p, Block b, Material mat) {
    Collection<ItemStack> drops = b.getDrops(p.getInventory().getItemInMainHand());
    int amount = drops.size();
    b.getLocation().getWorld().dropItemNaturally(b.getLocation(), new ItemStack(mat, amount));
  }
  
  public static void stoneMining(Location loc) {
    Random rdm = new Random();
    int luck = rdm.nextInt(100);
    if (luck < 4)
      loc.getWorld().spawnEntity(loc.add(0.5D, 0.5D, 0.5D), EntityType.SILVERFISH); 
    if (luck >= 99)
      for (int i = 0; i < 10; i++)
        loc.getWorld().spawnEntity(loc.add(0.5D, 0.5D, 0.5D), EntityType.SILVERFISH);  
  }
  
  public static void fuckUpHarvest(Player p, Block b, Material mat) {
	Random rdm = new Random();
	int luck = rdm.nextInt(100);
	World w = b.getWorld();
	int y = b.getY();
	System.out.println(luck);
	if (luck < 10) {
		for(int x = b.getX() - 5; x < b.getX() + 6; x++) {
		  for(int z = b.getZ() - 5; z < b.getZ() + 6; z++) {
			  if(w.getBlockAt(x, y, z).getType().equals(mat)) { // Que c'est le même type de crop
				  w.getBlockAt(x, y, z).setType(Material.AIR);
			  }
		  }
		}
		
		p.sendMessage(VoiceLines.getDestroyedCropsLine(0));
		return;
	}
	
	if(luck > 95) {
		b.setType(Material.LAVA);
		p.sendMessage(VoiceLines.getDestroyedCropsLine(1));
		return;
	}
	
	if( luck > 10 && luck < 30) {
		if(mat.equals(Material.POTATOES)) {
			for(int i = 0; i < luck; i++) {
				b.setType(Material.AIR);
				Item patate = w.dropItem(b.getLocation(), new ItemStack(Material.POISONOUS_POTATO));
				patate.setVelocity(new Vector(RandomBetween.randomDouble(-0.1, 0.1), RandomBetween.randomDouble(0.5, 1.25), RandomBetween.randomDouble(-0.1, 0.1)));
			}

			p.sendMessage(VoiceLines.getDestroyedCropsLine(2));
		}
	}
	
	
	  
	  
  }
}
