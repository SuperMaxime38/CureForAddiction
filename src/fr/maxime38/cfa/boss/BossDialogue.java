package fr.maxime38.cfa.boss;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossDialogue {
  JavaPlugin main;
  
  List<Player> players = new ArrayList<>();
  
  public BossDialogue(JavaPlugin main, List<Player> players) {
    this.main = main;
    this.players = players;
  }
  
  public void sendMessage(String msg) {
    for (Player p : players)
      p.sendMessage(msg); 
  }
  
  public void endBossPhase2() {
    (new BukkitRunnable() {
        long timer = 0L;
        
        public void run() {
          if (timer == 0L)
            sendMessage("§eI feel... §7tired"); 
          if (timer == 2L) {
            sendMessage("§7It's really exhausting to fly all day long");
            cancel();
            System.gc();
          } 
          this.timer++;
        }
      }).runTaskTimer(main, 0L, 20L);
  }
  
  public void endBossPhase3() {
    (new BukkitRunnable() {
        long timer = 0L;
        
        public void run() {
          if (timer == 0L)
            sendMessage("§cENOUGH !"); 
          if (timer == 1L)
            sendMessage("§cYou're very annoying you know ?"); 
          if (timer == 3L)
            sendMessage("§cLet's end this quickly"); 
          if (timer == 4L) {
            sendMessage("My minions won't even need my help");
            cancel();
            System.gc();
          } 
          timer++;
        }
      }).runTaskTimer(main, 0L, 10L);
  }
}
