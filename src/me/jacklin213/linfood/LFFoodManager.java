package me.jacklin213.linfood;

import java.util.ArrayList;
import java.util.Arrays;

public class LFFoodManager {

	public static LinFood plugin;
	private ArrayList<String> foodNameList = new ArrayList<String>(
			Arrays.asList("Apple", "Baked_Potato", "Bread", "Cake", "Carrot",
					"Cooked_Chicken", "Cooked_Fish", "Cooked_Porkchop",
					"Cookie", "Golden_Carrot", "Golden_Carrot", "Melon",
					"Mushroom_Stew", "Poisonous_Potato", "Potato",
					"Pumpkin_Pie", "Raw_Beef", "Raw_Chicken", "Raw_Fish",
					"Raw_Porkchop", "Rotten_Flesh", "Spider_Eye", "Steak"));
	private ArrayList<Integer> foodId = new ArrayList<Integer>(Arrays.asList(
			260, 393, 297, 354, 366, 350, 320, 357, 322, 396, 360, 282,
			394, 400, 363, 365, 349, 319, 367, 375, 364));

	public boolean contains(int id) {
		return this.foodId.contains(id);
	}

	public String getFoodNames(){
		String allFood = "";

		for (String s : this.foodNameList){
			allFood += s + ", ";
		}
		
		return allFood;
	}
}
