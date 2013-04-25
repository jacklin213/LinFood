package me.jacklin213.linfood;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LinFood extends JavaPlugin{
	
	public static LinFood plugin;
	public Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile;
	
	public LFMessages lfm = new LFMessages(this);
	public LFFoodManager lffm = new LFFoodManager();
	
	@Override
	public void onEnable(){
		log.info(String.format(
				"[%s] Version %s by jacklin213 has been Enabled!",
				getDescription().getName(), getDescription().getVersion(),
				getDescription().getAuthors()));
	}
	@Override
	public void onDisable(){
		log.info(String.format("[%s] Disabled Version %s", getDescription()
				.getName(), getDescription().getVersion()));
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		if (commandLabel.equalsIgnoreCase("linfood") || (commandLabel.equalsIgnoreCase("lf"))){
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("info")){
					lfm.info(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("give")){
					lfm.giveCommandHelp(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("help")){
					if (sender.hasPermission("linfood.help")){
						lfm.help(sender, 1);
						return true;
					} else {
						sender.sendMessage(lfm.permMessage);
						return true;
					}
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			}
			if (args.length == 2){
				if (args[0].equalsIgnoreCase("give")){
					if (sender.hasPermission("linfood.give.list")){
						if (args[1].equalsIgnoreCase("list")){
							String foodNames = lffm.getFoodNames();
							sender.sendMessage(lfm.chatPluginName + ChatColor.GOLD + "Foods: " + ChatColor.AQUA + foodNames);
							return true;
						}
					} else {
						sender.sendMessage(lfm.permMessage);
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("help")){
					int page = Integer.parseInt(args[1]);
					lfm.help(sender, page);
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			} 
			if (args.length == 3){
				if (args[0].equalsIgnoreCase("give")){
					if (sender.hasPermission("linfood.give")){
						Player player = Bukkit.getServer().getPlayerExact(args[1]);
						if (player != null){
							String food = args[2];
							try {
								int amount = 1;
								addFoodToInv(sender, player, food, amount);
								return true;
							} catch (NumberFormatException nfe){
								player.sendMessage(lfm.invalidNum);
								sender.sendMessage(lfm.giveUsage);
								return true;
							}
						} else {
							sender.sendMessage(lfm.invalidPlayer);
							sender.sendMessage(lfm.giveUsage);
							return true;
						}
					} else {
						sender.sendMessage(lfm.permMessage);
						return true;
					}
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			}
			if (args.length == 4){
				if (args[0].equalsIgnoreCase("give")){
					if (sender.hasPermission("linfood.give")){
						Player player = Bukkit.getServer().getPlayerExact(args[1]);
						if (player != null){
							String food = args[2];
							try {
								int amount = Integer.parseInt(args[3]);
								addFoodToInv(sender, player, food, amount);
								return true;
							} catch (NumberFormatException nfe){
								player.sendMessage(lfm.invalidNum);
								player.sendMessage(lfm.giveUsage);
								return true;
							}
						} else {
							sender.sendMessage(lfm.invalidPlayer);
							sender.sendMessage(lfm.giveUsage);
							return true;
						}
					} else {
						sender.sendMessage(lfm.permMessage);
						return true;
					}
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			}
			if (args.length > 4){
				sender.sendMessage(lfm.invalidCommand);
				return true;
			}
			lfm.basicInfo(sender);
			return true;
		}
		
		
		return false;
	}
	
	public void addFoodToInv(CommandSender sender, Player p, String food, int qty){
		try {
			PlayerInventory inventory = p.getInventory();
			Material item = Material.matchMaterial(food);
			if (item != null){
				String itemName = item.name();
				int itemId = item.getId();
				if (lffm.contains(itemId)){
					inventory.addItem(new ItemStack(item, qty));
					lfm.giveMessage(sender, itemName, qty, p);
				} else if ((item == Material.POTATO) || (item.getId() == 392)) {
					String potato = "POTATO";
					int potatoId = 392;
					inventory.addItem(new ItemStack(potatoId, qty));
					lfm.giveMessage(sender, potato, qty, p);
				} else if ((item == Material.CARROT) || (item.getId() == 391)) {
					String carrot = "CARROT";
					int carrotId = 391;
					inventory.addItem(new ItemStack(carrotId, qty));
					lfm.giveMessage(sender, carrot, qty, p);
				} else {
					sender.sendMessage(lfm.invalidFoodItem);
				}
			} else {
				sender.sendMessage(lfm.invalidFoodItem);
			}
		} catch (Exception e){
			e.printStackTrace();
			sender.sendMessage(ChatColor.RED + "Error, Please Check you have typed a valid Food or Amount");
		}
	}	
}
