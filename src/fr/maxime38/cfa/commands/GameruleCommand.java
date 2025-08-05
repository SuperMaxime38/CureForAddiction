package fr.maxime38.cfa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameruleCommand implements CommandExecutor {
  public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
    s.sendMessage("Do you really think that i will ALLOW you to change MY rules");
    s.sendMessage("The goal of this plugin is to make you LEAVE the game");
    s.sendMessage("That's why it is §cHARD");
    s.sendMessage("§aEnjoy the ride >:)");
    return true;
  }
}
