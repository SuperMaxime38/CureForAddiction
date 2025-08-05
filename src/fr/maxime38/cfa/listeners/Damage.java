package fr.maxime38.cfa.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.utils.BlockManagement;
import fr.maxime38.cfa.utils.RandomBetween;
import fr.maxime38.cfa.utils.RandomColors;
import fr.maxime38.cfa.utils.RemplacableBlocks;
import fr.maxime38.cfa.utils.VoiceLines;

public class Damage implements Listener {
  static Main main;
  
  public Damage(Main main) {
    Damage.main = main;
  }
  
  @EventHandler
  public static void damageEntity(EntityDamageByEntityEvent e) {
    ItemStack item;
    Item it;
    int i;
    if (!(e.getDamager() instanceof Player))
      return; 
    Player p = (Player)e.getDamager();
    Entity ent = e.getEntity();
    Location locE = ent.getLocation().clone();
    Location loc = p.getLocation().clone();
    Random rdm = new Random();
    switch (ent.getType()) {
      case SHEEP:
        item = p.getInventory().getItemInMainHand();
        it = p.getWorld().dropItemNaturally(p.getLocation(), item);
        it.setVelocity(p.getLocation().getDirection().multiply(RandomBetween.randomDouble(0.2D, 1.0D)).add(new Vector(0.0D, 0.3D, 0.0D)));
        item.setType(RandomColors.getRandomWoolColor());
        int luck = rdm.nextInt(100);
        if (luck < 10)
          p.sendMessage(VoiceLines.getSheepLine()); 
		break;
      case ZOMBIE:
        p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 1));
        break;
      case SKELETON:
        ent.getWorld().spawnArrow(ent.getLocation().clone().add(0.0D, 2.0D, 0.0D), new Vector(0.0D, 1.6D, 0.0D), 0.9F, 0.55F);
		break;
      case SPIDER:
        spawnWebs(locE, 1, 3);
		break;
      case CHICKEN:
        ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.2F, 0.6F);
        fireworkChicken(p, (LivingEntity)ent);
		break;
      case PIG:
        luck = rdm.nextInt(100);
        if (luck < 25)
          ent.getWorld().spawnEntity(locE, EntityType.ZOMBIFIED_PIGLIN);
		break;
      case VILLAGER:
        loc.getBlock().setType(Material.STICKY_PISTON);
        BlockManagement.orientBlock(loc, BlockFace.UP);
        loc.add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.END_ROD);
        for (i = 0; i < 10; i++) {
          loc = loc.add((rdm.nextInt(20) - 10), 0.0D, (rdm.nextInt(20) - 10));
          loc.setY(loc.getWorld().getHighestBlockYAt(loc));
          IronGolem golem = (IronGolem)p.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
          golem.setTarget((LivingEntity)p);
        }
		break;
      case RABBIT:
        ent.setFallDistance(64.0F);
        ent.setVelocity(ent.getVelocity().multiply(5).add(new Vector(0, 8, 0)));
		break;
      case CAT:
        ent.setInvulnerable(true);
        ent.getWorld().spawnEntity(ent.getLocation(), EntityType.RAVAGER);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 50, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
		break;
      case WOLF:
        p.playSound(loc, Sound.ENTITY_WOLF_HOWL, 0.0F, 0.0F);
        luck = rdm.nextInt(100);
        if (luck < 50) {
          for (i = 0; i < 16; i++) {
            Wolf wolf = (Wolf)p.getWorld().spawnEntity(loc, EntityType.WOLF);
            wolf.setTarget((LivingEntity)p);
          } 
          p.sendMessage("§cThe pack will always help its members");
          
        }
		break;
      case COW:
        luck = rdm.nextInt(100);
        if (luck > 65) {
          if (luck > 90) {
            p.damage(0.5D);
            double x = loc.getX() - locE.getX();
            double y = loc.getY() - locE.getY();
            double z = loc.getZ() - locE.getZ();
            p.setVelocity((new Vector(x, y, z)).multiply(0.5D));
          } else {
            p.damage(0.75D);
          } 
          luck = rdm.nextInt(100);
          if (luck > 60)
            p.sendMessage(VoiceLines.getCowLine()); 
        }
		break;
      case IRON_GOLEM:
        luck = rdm.nextInt(100);
        if (luck < 10) {
          double x = loc.getX() - locE.getX();
          double z = loc.getZ() - locE.getZ();
          ent.setVelocity(ent.getVelocity().add(new Vector(x / 2.0D, 1.2D, z / 2.0D)));
        } 
		break;
      case WITHER_SKELETON:
        luck = rdm.nextInt(100);
        if(luck > 75) {
        	for(i = 0; i < 5; i++) {
        		Location lc = locE.clone().add(RandomBetween.randomDouble(-3, 3), -1, RandomBetween.randomDouble(-3, 3));
        		if(lc.getBlock().getType().equals(Material.NETHER_BRICKS)) {
	        		lc.getBlock().setType(Material.SOUL_SAND);
        		}
        	}
        }
        if (luck < 15)
          p.playSound(loc, Sound.ENTITY_WITHER_AMBIENT, 1.0F, 1.0F); 
		break;
      case GUARDIAN: 
	    luck = rdm.nextInt(100);
	    if (luck < 10) {
	      ent.getWorld().spawnEntity(locE, EntityType.GUARDIAN);
	      ent.getWorld().spawnEntity(locE, EntityType.GUARDIAN);
	      ent.getWorld().spawnEntity(locE, EntityType.GUARDIAN);
	      ent.getWorld().spawnEntity(locE, EntityType.GUARDIAN);
	    }
        break;
      case ZOMBIFIED_PIGLIN:
    	  
    	  luck = rdm.nextInt(100);
		  if (luck < 95) { // 95 % de chance

			  item = new ItemStack(Material.NETHERITE_SWORD);
			  ItemMeta meta = item.getItemMeta();
			  meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
			  for(Entity entity : p.getNearbyEntities(32, 32, 32)) {
				  if(entity instanceof PigZombie) {
					  ((PigZombie)entity).getEquipment().setItemInMainHand(item);
					  ((PigZombie) entity).setTarget(p);
					  ((PigZombie) entity).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 180, 2));
					  
					  if(luck < 5) { // cumulatif
						  locE.getWorld().spawnEntity(locE, EntityType.ZOMBIFIED_PIGLIN);
					  }
				  }
			  }
		  }
    	  
    	  break;
      default:
        return;
    }
  }
  
  @EventHandler
  public static void damageTaken(EntityDamageByEntityEvent e) {
    int x, z;
    Location loc;
    int luck, signX, signZ, i;
    if (!(e.getEntity() instanceof Player))
      return; 
    Player p = (Player)e.getEntity();
    Entity damager = e.getDamager();
    Random rdm = new Random();
    switch (damager.getType()) {
      case IRON_GOLEM:
        p.setVelocity(p.getVelocity().multiply(2).add(new Vector(0, 3, 0)));
        return;
      case ENDERMAN:
        x = rdm.nextInt(20) - 10;
        z = rdm.nextInt(20) - 10;
        loc = new Location(p.getWorld(), p.getLocation().getX() + x, (p.getWorld().getHighestBlockAt(x, z).getY() + 1), p.getLocation().getZ() + z);
        p.teleport(loc);
        return;
      case PHANTOM:
        p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 1));
        return;
      case SILVERFISH:
        luck = rdm.nextInt(100);
        if (luck > 50)
          for (int j = 0; j < rdm.nextInt(4) + 1; j++)
            p.getWorld().spawnEntity(damager.getLocation(), EntityType.SILVERFISH);  
        return;
      case BLAZE:
        signX = rdm.nextInt(3) - 2;
        signZ = rdm.nextInt(3) - 2;
        p.setVelocity(p.getVelocity().add(new Vector(signX * 0.25D, 0.5D, signZ * 0.25D)));
        luck = rdm.nextInt(100);
        if (luck < 10) {
          x = rdm.nextInt(60) - 30;
          z = rdm.nextInt(60) - 30;
          loc = new Location(damager.getWorld(), damager.getLocation().getX() + x, damager.getLocation().getY(), damager.getLocation().getZ() + z);
          loc.getWorld().spawnEntity(loc, EntityType.GHAST);
        } 
        return;
      case ZOMBIE:
        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 6, 1));
        return;
      case SPIDER:
        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 8, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 6, 1));
        return;
      case PILLAGER:
        p.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 10, 1));
        for (i = 0; i < 8; i++)
          p.getWorld().spawnArrow(p.getLocation().clone().add(0.0D, 2.0D, 0.0D), new Vector(0.0D, 1.6D, 0.0D), 1.95F, 2.1F); 
        return;
      case RAVAGER:
        luck = rdm.nextInt(100);
        if (luck < 10) {
          x = rdm.nextInt(40) - 20;
          z = rdm.nextInt(40) - 20;
          loc = new Location(p.getWorld(), p.getLocation().getX() + x, (p.getWorld().getHighestBlockAt(x, z).getY() + 1), p.getLocation().getZ() + z);
          loc.getWorld().spawnEntity(loc, EntityType.PILLAGER);
        } 
        return;
      case GUARDIAN:
        p.setRemainingAir(p.getRemainingAir() - 5);
        return;
	default:
		break;
    } 
  }
  
  @EventHandler
  public static void randomDamages(EntityDamageEvent e) {
    int luck;
    Random rdm = new Random();
    Entity entity = e.getEntity();
    Player p = null;
    if (entity instanceof Player)
      p = (Player)entity; 
    switch (e.getCause()) {
      default:
        return;
      case FIRE_TICK:
        luck = rdm.nextInt(100);
        if (luck > 60) {
          Location loc = entity.getLocation().clone();
          loc.add(0.0D, 1.0D, 0.0D);
          if (RemplacableBlocks.contains(loc.getBlock().getType().toString()))
            loc.getBlock().setType(Material.FIRE); 
        } 
        if (luck < 20 && 
          p != null) {
          int signX = rdm.nextInt(3) - 2;
          int signZ = rdm.nextInt(3) - 2;
          p.setVelocity(p.getVelocity().add(new Vector(signX * 0.25D, 0.5D, signZ * 0.25D)));
        } 
        return;
      case LIGHTNING:
        break;
    } 
    if (p != null) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 4, 1));
      luck = rdm.nextInt(100);
      if (luck < 1)
        for (int i = 0; i < 3; i++) {
          int x = rdm.nextInt(60) - 30;
          int z = rdm.nextInt(60) - 30;
          Location loc = new Location(p.getWorld(), p.getLocation().getX() + x, (p.getWorld().getHighestBlockAt(x, z).getY() + 1), p.getLocation().getZ() + z);
          Creeper creeper = (Creeper)loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
          creeper.setPowered(true);
        }  
    } 
  }
  
  @EventHandler
  public static void onExplode(EntityExplodeEvent e) {
    if (e.getEntity() instanceof Creeper) {
      Creeper c = (Creeper)e.getEntity();
      Location loc = e.getLocation();
      spawnCreepers(c, loc);
    } 
  }
  
  @EventHandler
  public static void onKill(EntityDeathEvent e) {
    if (!(e.getEntity().getKiller() instanceof Player))
      return; 
    Player killer = e.getEntity().getKiller();
    LivingEntity livingEntity = e.getEntity();
    Location locE = livingEntity.getLocation();
    if (!(e.getEntity() instanceof Player)) {
      int i;
      switch (livingEntity.getType()) {
        case SKELETON:
          for (i = 0; i < 32; i++)
            livingEntity.getWorld().spawnArrow(livingEntity.getLocation().clone().add(0.0D, 2.0D, 0.0D), new Vector(0.0D, 1.6D, 0.0D), 1.95F, 2.1F); 
          break;
        case ZOMBIE:
          killer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
          killer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 80, 2));
          killer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1));
          break;
        case CREEPER:
          if (livingEntity.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || livingEntity.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
            spawnCreepers((Creeper)livingEntity, livingEntity.getLocation()); 
          break;
        case SPIDER:
          spawnWebs(locE, 16, 6);
          break;
	default:
		break;
      } 
    } 
  }
  
  public static void spawnCreepers(Creeper c, final Location loc) {
    if (c.isPowered()) {
      Random rdm = new Random();
      for (int i = 0; i < 8; i++) {
        Location location = loc.clone();
        int X = rdm.nextInt(17);
        int Z = rdm.nextInt(17);
        location.add((X - 8), 0.0D, (Z - 8));
        loc.getWorld().strikeLightning(location);
      } 
      (new BukkitRunnable() {
          public void run() {
            Creeper result = (Creeper)loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
            result.setPowered(true);
            result = (Creeper)loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
            result.setPowered(true);
          }
        }).runTaskLater((Plugin)main, 5L);
    } else {
      loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
      loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
    } 
  }
  
  public static void spawnWebs(Location loc, int amount, int radius) {
    Random rdm = new Random();
    for (int i = 0; i < amount; i++) {
      int X = rdm.nextInt(radius * 2);
      int Z = rdm.nextInt(radius * 2);
      Location location = loc.getWorld().getHighestBlockAt(loc.getBlockX() + X - radius, loc.getBlockZ() + Z - radius).getLocation();
      location.add(0.0D, 1.0D, 0.0D);
      location.getBlock().setType(Material.COBWEB);
    } 
  }
  
  public static void fireworkChicken(final Player p, final LivingEntity ent) {
    (new BukkitRunnable() {
        int fuse = 0;
        
        public void run() {
          if (this.fuse == 8) {
            ent.setHealth(0.0D);
            Firework fw = (Firework)ent.getWorld().spawnEntity(ent.getLocation(), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(3);
            Color c = RandomColors.getRandomColor();
            fwm.addEffect(FireworkEffect.builder().withColor(RandomColors.getRandomColor()).flicker(true).build());
            fw.setFireworkMeta(fwm);
            fw.detonate();
            ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6.0F, 0.0F);
            double radius = 2.5D;
            double damage = 8.5D;
            List<Entity> area = ent.getNearbyEntities(radius, radius, radius);
            for (Entity e : area) {
              if (e instanceof LivingEntity) {
                LivingEntity le = (LivingEntity)e;
                le.damage(damage * (radius - le.getLocation().distance(ent.getLocation())) / radius);
              } 
            } 
            Item it = ent.getWorld().dropItem(ent.getLocation(), new ItemStack(Material.FEATHER));
            it.setCustomName(c + it.getCustomName());
            it.setVelocity(new Vector(RandomBetween.randomDouble(-0.22D, -0.12D), RandomBetween.randomDouble(0.005D, 0.05D), RandomBetween.randomDouble(0.11D, 0.19D)));
            Item it2 = ent.getWorld().dropItem(ent.getLocation(), new ItemStack(Material.FEATHER));
            it2.setVelocity(new Vector(RandomBetween.randomDouble(0.11D, 0.2D), RandomBetween.randomDouble(0.01D, 0.02D), RandomBetween.randomDouble(0.04D, 0.075D)));
            it2.setCustomName(c + it.getCustomName());
            Item it3 = ent.getWorld().dropItem(ent.getLocation(), new ItemStack(Material.FEATHER));
            it3.setVelocity(new Vector(RandomBetween.randomDouble(-0.1D, -0.13D), RandomBetween.randomDouble(0.005D, 0.6D), RandomBetween.randomDouble(-0.06D, -0.105D)));
            it3.setCustomName(c + it.getCustomName());
            ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 3.0F, 1.0F);
            Random rdm = new Random();
            int luck = rdm.nextInt(100);
            if (luck < 15)
              p.sendMessage(VoiceLines.getChickenDamageLine()); 
            cancel();
            return;
          } 
          ent.setVelocity(new Vector(0.0D, 0.725D, 0.0D));
          this.fuse++;
        }
      }).runTaskTimer((Plugin)main, 0L, 5L);
  }
  
  @EventHandler
  public void onItemMerge(ItemMergeEvent e) {
    if (e.getEntity().getItemStack().getType() == Material.FEATHER && 
      !e.getEntity().isOnGround())
      e.setCancelled(true); 
  }
}
