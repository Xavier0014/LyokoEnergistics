package com.xavier0014.lyokoenergistics.init;

import com.xavier0014.lyokoenergistics.items.*;
import com.xavier0014.lyokoenergistics.items.record.*;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem {
	
	public static final ItemLE matteringot = (ItemLE) new ItemLE("matteringot");
	public static final RecordLE UnMondeSansDanger = new RecordLE("unmondesansdanger","Code Lyoko - Un monde sans danger");
	
	
	public static void init(){
		
		GameRegistry.registerItem(matteringot, "matteringot");
		GameRegistry.registerItem(UnMondeSansDanger, "UnMondeSansDanger");
	}

}
