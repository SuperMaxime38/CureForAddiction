package fr.maxime38.cfa.commands;

import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.boss.EndBoss;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DevCommand implements CommandExecutor {
  static EndBoss boss1;
  
  static Main main;
  
  private List<String> devs;
  
  public DevCommand(Main main) {
    this.devs = Arrays.asList(new String[] { "PoutreCosmique", "TTgame26" });
    DevCommand.main = main;
  }
  
  public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
    if (!(s instanceof Player)) {
      s.sendMessage("Only developpers can do this command IN GAME");
      return true;
    } 
    Player p = (Player)s;
    if (!this.devs.contains(p.getName())) {
      s.sendMessage("Only developpers can do this command (diabolic smile)");
      return true;
    } 
    if (args.length == 0) {
      s.sendMessage("§aList of command for §6/cfadev\n§acfadev boss §f<boss> : spawns the boss (at the rigth coords btw)\n§a/cfadev §fbon ya r d'autre pr l'instant");
      return true;
    } 
    if (args.length == 1) {
      String str;
      switch ((str = args[0]).hashCode()) {
        case 3029869:
          if (!str.equals("boss"))
            break; 
          s.sendMessage("§aList of bosses §f:\n - §6ender §f: The Ender");
          return true;
      } 
    } 
    if (args.length == 2) {
      String str2;
      String str1;
      switch ((str1 = args[0]).hashCode()) {
        case 3029869:
          if (!str1.equals("boss"))
            break; 
          switch ((str2 = args[1]).hashCode()) {
            case 96651976:
              if (!str2.equals("ender"))
                break; 
              boss1 = new EndBoss(main);
              boss1.theEnder();
              return true;
          } 
          break;
      } 
    } 
    return false;
  }
  
  public static boolean hasBossStarted() {
    if (boss1 != null)
      return true; 
    return false;
  }
  
  public static void killBosses() {
    boss1.killBoss();
  }
}
