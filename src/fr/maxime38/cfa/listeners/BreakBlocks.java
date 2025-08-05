package fr.maxime38.cfa.listeners;


import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.utils.VoiceLines;
import java.util.Collection;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

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
      default:
        return;
      case POTATO:
        break;
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
}
