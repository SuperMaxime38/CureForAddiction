package fr.maxime38.cfa.boss;

import fr.maxime38.cfa.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EndBoss implements Listener {
  static Main main;
  
  List<Player> playa = new ArrayList<>();
  
  LivingEntity e;
  
  BossBar bossBar;
  
  List<Entity> minions = new ArrayList<>();
  
  static HashMap<LivingEntity, Player> targets = new HashMap<>();
  
  static int phase = 0;
  
  World world;
  
  Random rdm = new Random();
  
  List<EntityType> picks = Arrays.asList(new EntityType[] { EntityType.WITHER_SKELETON, EntityType.SKELETON, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.WITHER_SKELETON });
  
  BossDialogue dg;
  
  public EndBoss(Main main) {
    EndBoss.main = main;
  }
  
  public void theEnder() {
    phase = 1;
    this.e = (LivingEntity)Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 83.5D, 16.0D, 8.5D, 90.0F, 0.0F), EntityType.WITHER_SKELETON);
    this.e.getEquipment().setHelmet(new ItemStack(Material.DRAGON_HEAD));
    this.e.setAI(false);
    this.e.setInvulnerable(true);
    this.e.setSilent(true);
    this.e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0D);
    this.e.setHealth(100.0D);
    this.bossBar = Bukkit.createBossBar(
        ChatColor.DARK_PURPLE + "The Ender" + ChatColor.AQUA, 
        BarColor.GREEN, 
        BarStyle.SOLID, new org.bukkit.boss.BarFlag[0]);
    List<Entity> around = this.e.getNearbyEntities(116.0D, 8.0D, 116.0D);
    for (Entity ent : around) {
      if (ent instanceof Player) {
        if (this.world == null)
          this.world = ent.getWorld(); 
        this.playa.add((Player)ent);
        ent.sendMessage("Welcome adventurers\n");
        this.bossBar.addPlayer((Player)ent);
      } 
    } 
    this.dg = new BossDialogue((JavaPlugin)main, this.playa);
    (new BukkitRunnable() {
        int timer = 0;
        
        public void run() {
          if (this.timer == 0) {
            EndBoss.this.sendInEnderArena("Unfortunately for you, your journey stops here\n\n");
            EndBoss.this.e.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 2));
          } 
          if (this.timer == 3)
            EndBoss.this.sendInEnderArena("It is the §6END for you !\n§oIm so funny"); 
          if (this.timer == 5) {
            EndBoss.this.e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 9999, 254));
            EndBoss.this.sendInEnderArena("§cYou have no idea what will happen to you");
            EndBoss.this.summonMinions(1);
            EndBoss.this.gameTimer();
            cancel();
            System.gc();
          } 
          this.timer++;
        }
      }).runTaskTimer((Plugin)main, 40L, 20L);
  }
  
  public void gameTimer() {
    (new BukkitRunnable() {
        double percentage = 0.0D;
        
        long timer = 0L;
        
        long delta = 0L;
        
        public void run() {
          this.percentage = EndBoss.this.e.getHealth() / EndBoss.this.e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
          EndBoss.this.bossBar.setProgress(this.percentage);
          if (!EndBoss.targets.keySet().isEmpty())
            for (LivingEntity e : EndBoss.targets.keySet())
              ((Mob)e).setTarget((LivingEntity)EndBoss.targets.get(e));  
          if (EndBoss.phase == 1 && EndBoss.targets.isEmpty()) {
            EndBoss.phase = 2;
            EndBoss.this.dg.endBossPhase2();
            EndBoss.this.e.getActivePotionEffects().clear();
            EndBoss.this.e.setInvulnerable(false);
          } 
          if (EndBoss.phase == 2 && this.percentage <= 0.66D)
            if (this.delta == 0L) {
              this.delta = this.timer;
              EndBoss.this.e.setInvulnerable(true);
              EndBoss.this.e.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 2));
              EndBoss.this.e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 9999, 254));
              EndBoss.this.dg.endBossPhase3();
            } else if (this.timer - this.delta >= 40L) {
              this.delta = 0L;
              EndBoss.this.summonMinions(2);
              EndBoss.phase = 3;
              EndBoss.this.e.setHealth(66.0D);
            }  
          this.timer++;
        }
      }).runTaskTimer((Plugin)main, 0L, 1L);
  }
  
  public void sendInEnderArena(String msg) {
    for (Player p : this.playa)
      p.sendMessage(msg); 
  }
  
  public void summonMinions(int vague) {
    for (int i = 0; i < 4; i++) {
      for (Player p : this.playa) {
        for (int j = 0; j < 3 * vague; j++) {
          LivingEntity e = (LivingEntity)this.world.spawnEntity(getClusterLoc(i), this.picks.get(this.rdm.nextInt(this.picks.size())));
          if (vague == 1) {
            e.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
          } else {
            e.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            if (e instanceof org.bukkit.entity.WitherSkeleton)
              e.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD)); 
            if (e instanceof org.bukkit.entity.Skeleton)
              e.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 2)); 
          } 
          this.minions.add(e);
          targets.put(e, p);
        } 
      } 
    } 
  }
  
  public Location getClusterLoc(int i) {
    switch (i) {
      default:
        return new Location(this.world, 100.0D, 17.0D, 8.0D);
      case 1:
        return new Location(this.world, 83.0D, 17.0D, 25.0D);
      case 2:
        return new Location(this.world, 66.0D, 17.0D, 8.0D);
      case 3:
        break;
    } 
    return new Location(this.world, 83.0D, 17.0D, -9.0D);
  }
  
  public void killBoss() {
    if (this.e != null)
      this.e.setHealth(0.0D); 
    if (this.bossBar != null)
      this.bossBar.removeAll(); 
  }
  
  @EventHandler
  public static void cancelTKForMonsters(EntityDamageByEntityEvent e) {
    if (!(e.getEntity() instanceof LivingEntity))
      return; 
    if (!(e.getDamager() instanceof LivingEntity))
      return;
    LivingEntity entity = (LivingEntity)e.getEntity();
    LivingEntity dmg = (LivingEntity)e.getDamager();
    if (!targets.containsKey(entity))
      return; 
    if (!targets.containsKey(dmg))
      return; 
    System.out.println("canceled damage" + dmg.getType().toString());
    e.setDamage(0.0D);
  }
  
  @EventHandler
  public static void deathHandler(EntityDeathEvent e) {
    LivingEntity entity = e.getEntity();
    if (targets.containsKey(entity)) {
      targets.remove(entity);
      e.getDrops().clear();
    }
  }
}