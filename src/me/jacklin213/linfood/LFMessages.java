package me.jacklin213.linfood;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class LFMessages {
	
	public static LinFood plugin;
	public String pluginName = "LinFood";
	
	public String author = "jacklin213";
	public String chatPluginName = ChatColor.WHITE + "[" + ChatColor.GREEN + pluginName + ChatColor.WHITE + "] ";
	public String description = "A /give plugin for food only";
	public String invalidCommand = ChatColor.RED + "Not a valid command , Do /linfood help";
	public String invalidFoodItem = ChatColor.RED + "Invalid food type";
	public String invalidNum = ChatColor.RED + "Invalid number";
	public String invalidPlayer = ChatColor.RED + "Invalid Player";
	public String playerOnly = ChatColor.RED + "This is a player only Command!";
	public String permMessage = ChatColor.RED + "You do not have the permissions to use this command!";
	public String version = "v1.0";
	
	public LFMessages(LinFood instance) {
		plugin = instance;
	}

	public void basicInfo(CommandSender sender){
		sender.sendMessage(chatPluginName + "made by" + ChatColor.GOLD + " jacklin213"); 
	}
	
	public void info(CommandSender sender){
		sender.sendMessage(ChatColor.GOLD + " ============ " + chatPluginName + ChatColor.GOLD + " ============ ");
		sender.sendMessage(ChatColor.GOLD + "Plugin name: " + ChatColor.AQUA + pluginName);
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + version);
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA  + "by " + author);
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + description);
	}
	
	public void help(CommandSender sender, int page){
		try {
            if (page == 1) {
                sender.sendMessage(ChatColor.GOLD + "============" + ChatColor.WHITE + "[" + ChatColor.GREEN + pluginName + ChatColor.WHITE + ":" + ChatColor.YELLOW + "Help" + ChatColor.WHITE + "]" + ChatColor.GOLD + " ============ ");
                sender.sendMessage(ChatColor.GOLD + "Page" + ChatColor.WHITE + "(" + ChatColor.AQUA + "1" + ChatColor.WHITE + "/" + ChatColor.AQUA + "1" + ChatColor.WHITE + ")" );
                sender.sendMessage(ChatColor.GOLD + "/linfood " + ChatColor.WHITE + ":" + ChatColor.AQUA + "Shows plugin name and author");
                sender.sendMessage(ChatColor.GOLD + "/linfood info" + ChatColor.WHITE + ":" + ChatColor.AQUA + "Shows the full plugin info");
                sender.sendMessage(ChatColor.GOLD + "/linfood help" + ChatColor.WHITE + ":" + ChatColor.AQUA + "Displays this page");
                sender.sendMessage(ChatColor.GOLD + "/linfood give <item> <amount>" + ChatColor.WHITE + ":" + ChatColor.AQUA + "Give command for food");
            }
        } catch (NumberFormatException nfe) {
            sender.sendMessage(ChatColor.RED + " Invalid page number specified. There is only 1 page right now.");
        }
	}
}
