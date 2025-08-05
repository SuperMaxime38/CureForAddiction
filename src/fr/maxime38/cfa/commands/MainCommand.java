package fr.maxime38.cfa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
  public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
    s.sendMessage("this plugin is made for YOU lil addict");
    s.sendMessage("§cIt is made so that you will leave the game because of RAGE QUIT");
    s.sendMessage("§aGood luck >:)");
    return true;
  }
}