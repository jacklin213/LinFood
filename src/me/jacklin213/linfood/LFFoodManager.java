package me.jacklin213.linfood;

import java.util.ArrayList;
import java.util.Arrays;

public class LFFoodManager {
	
	public static LinFood plugin;

	private ArrayList<Integer> foodId = new ArrayList<Integer>(Arrays.asList(
			260, 393, 297, 354, 391, 366, 350, 320, 357, 322, 396, 360, 282,
			394, 392, 400, 363, 365, 349, 319, 367, 375, 364));
	
	public boolean contains(int id){
		return this.foodId.contains(id);
	}
}
