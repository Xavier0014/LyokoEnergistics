package com.xavier0014.lyokoenergistics.init;

import com.xavier0014.lyokoenergistics.items.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItem {
	
	public static final ItemLE matteringot = new ItemLE("matteringot");
	
	
	public static void init(){
		
		GameRegistry.registerItem(matteringot, "matteringot");
	}

}