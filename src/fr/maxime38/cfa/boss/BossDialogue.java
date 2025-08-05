package fr.maxime38.cfa.boss;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
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
    for (Player p : this.players)
      p.sendMessage(msg); 
  }
  
  public void endBossPhase2() {
    (new BukkitRunnable() {
        long timer = 0L;
        
        public void run() {
          if (this.timer == 0L)
            BossDialogue.this.sendMessage("§eI feel... §7tired"); 
          if (this.timer == 2L) {
            BossDialogue.this.sendMessage("§7It's really exhausting to fly all day long");
            cancel();
            System.gc();
          } 
          this.timer++;
        }
      }).runTaskTimer((Plugin)this.main, 0L, 20L);
  }
  
  public void endBossPhase3() {
    (new BukkitRunnable() {
        long timer = 0L;
        
        public void run() {
          if (this.timer == 0L)
            BossDialogue.this.sendMessage("§cENOUGH !"); 
          if (this.timer == 1L)
            BossDialogue.this.sendMessage("§cYou're very annoying you know ?"); 
          if (this.timer == 3L)
            BossDialogue.this.sendMessage("§cLet's end this quickly"); 
          if (this.timer == 4L) {
            BossDialogue.this.sendMessage("My minions won't even need my help");
            cancel();
            System.gc();
          } 
          this.timer++;
        }
      }).runTaskTimer((Plugin)this.main, 0L, 10L);
  }
}
