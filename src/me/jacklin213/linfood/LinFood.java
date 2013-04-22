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
				if (args[0].equalsIgnoreCase("help")){
					lfm.help(sender, 1);
					return true;
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			}
			if (args.length == 2){
				if (args[0].equalsIgnoreCase("help")){
					int page = Integer.parseInt(args[1]);
					lfm.help(sender, page);
				} else {
					sender.sendMessage(lfm.invalidCommand);
					return true;
				}
			}
			if (args.length >= 2 && args.length < 4){
				if (sender instanceof Player){
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("give")){
						String food = args[1];
						int amount;
						if (args[2] != ""){
							try {
								amount = Integer.parseInt(args[2]);
								addFoodToInv(sender, player, food, amount);
								return true;
							} catch (NumberFormatException nfe){
								player.sendMessage(lfm.invalidNum);
								return true;
							}
						} else {
							addFoodToInv(sender, player, food, 1);
							return true;
						}
						
					} else {
						sender.sendMessage(lfm.invalidCommand);
						return true;
					}
				} else {
					sender.sendMessage(lfm.playerOnly);
					return true;
				}
			}
			if (args.length == 4){
				if (args[0].equalsIgnoreCase("give")){
					Player player = Bukkit.getServer().getPlayerExact(args[1]);
					if (player != null){
						String food = args[2];
						try {
							int amount = Integer.parseInt(args[3]);
							addFoodToInv(sender, player, food, amount);
							return true;
						} catch (NumberFormatException nfe){
							player.sendMessage(lfm.invalidNum);
						}
					} else {
						sender.sendMessage(lfm.invalidPlayer);
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
				int itemId = item.getId();
				if (lffm.contains(itemId)){
					inventory.addItem(new ItemStack(item, qty));
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
	
	/*02:01	Didz	My guess is that youre Material.getMaterial(String) call is returning null
02:01	Didz	since "carrot" is not a valid name in the Material enum
02:01	Didz	use matchMaterial instead, and make sure to check if it's null when it returns
02:02	Didz	s/youre/your/
02:03		_Rogue_ detects bad regexp
02:03	jacklin213	kk
02:03	jacklin213	wat happens if they type a number
02:03	jacklin213	like the id for carrot
02:03	Didz	it will return null
02:03	_Rogue_	if it can't match it re-
02:03	_Rogue_	
02:03		*** ModMasta quit (Quit: Web client closed)
02:04	Didz	if you want to match a number ID, you'll need to use getMaterial(int)
02:04	Didz	and for that you'll need to Integer.parse the number from the command
02:04	Didz	so it's an integer not a string
02:04	Didz	cause it does an entirely different lookup if you pass it a string instead of an integer
02:04	Didz	yay method overloading
	 * 
	 * */
}
