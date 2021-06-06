package com.bitquest.bitquest.commands;

import com.bitquest.bitquest.BitQuest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrencyCommand extends CommandAction {
  private BitQuest bitQuest;

  public CurrencyCommand(BitQuest plugin) {
    bitQuest = plugin;
  }

  public boolean run(
      CommandSender sender, Command cmd, String label, String[] args, final Player player) {
    // CHANGE CURRENCY BY BITCOINJAKE09
    if (cmd.getName().equalsIgnoreCase("currency")) {

      if ((args[0].equalsIgnoreCase("ems"))
          || (args[0].equalsIgnoreCase("emerald"))
          || (args[0].equalsIgnoreCase("emeralds"))) {
        bitQuest.redis.set("currency" + player.getUniqueId().toString(), "emerald");
        player.sendMessage(
            ChatColor.GREEN
                + "Currency changed to "
                + bitQuest.redis.get("currency" + player.getUniqueId().toString()));

      } else if (args[0].equalsIgnoreCase(BitQuest.DENOMINATION_NAME)) {
        bitQuest.redis.set(
            "currency" + player.getUniqueId().toString(), BitQuest.DENOMINATION_NAME);
        player.sendMessage(
            ChatColor.GREEN
                + "Currency changed to "
                + bitQuest.redis.get("currency" + player.getUniqueId().toString()));

      } else {
        player.sendMessage(ChatColor.RED + "There was a problem changing your currency.");
      }
      try {
        bitQuest.updateScoreboard(player);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return true;
    }
    return false;
  }
}
