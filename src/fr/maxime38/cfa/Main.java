package fr.maxime38.cfa;

import org.bukkit.plugin.java.JavaPlugin;

import fr.maxime38.cfa.boss.EndBoss;
import fr.maxime38.cfa.commands.DevCommand;
import fr.maxime38.cfa.commands.GameruleCommand;
import fr.maxime38.cfa.commands.MainCommand;
import fr.maxime38.cfa.events.CustomEventsManager;
import fr.maxime38.cfa.listeners.BreakBlocks;
import fr.maxime38.cfa.listeners.Damage;
import fr.maxime38.cfa.listeners.Interact;
import fr.maxime38.cfa.listeners.JumpEvent;
import fr.maxime38.cfa.listeners.Move;
import fr.maxime38.cfa.listeners.PlaceBlocks;
import fr.maxime38.cfa.listeners.ProjectileLaunch;

public class Main extends JavaPlugin {
  public void onEnable() {
    saveDefaultConfig();
    getServer().getPluginManager().registerEvents(new BreakBlocks(this), this);
    getServer().getPluginManager().registerEvents(new PlaceBlocks(), this);
    getServer().getPluginManager().registerEvents(new Interact(), this);
    getServer().getPluginManager().registerEvents(new Move(), this);
    getServer().getPluginManager().registerEvents(new Damage(this), this);
    getServer().getPluginManager().registerEvents(new EndBoss(this), this);
    getServer().getPluginManager().registerEvents(new ProjectileLaunch(this), this);
    getServer().getPluginManager().registerEvents(new JumpEvent(), this);
    getCommand("cfa").setExecutor(new MainCommand());
    getCommand("cfadev").setExecutor(new DevCommand(this));
    if (getConfig().getBoolean("better-boss") && getServer().getPluginManager().getPlugin("WorldEdit") != null)
      System.out.println("[Cure For Addiction] > enabling CFA Boss (coming soon)"); 
    
    if(!getConfig().getBoolean("allow-cheats")) {
    	getCommand("gamerule").setExecutor(new GameruleCommand());
    }
    
    CustomEventsManager.run(this);
    
    System.out.println("[Cure For Addiction] > Pain begins");
  }
  
  public void onDisable() {
    saveConfig();
    if (DevCommand.hasBossStarted())
      DevCommand.killBosses(); 
    System.out.println("[Cure For Addiction] > Nice give up bro (or server reload idc)");
  }
}

