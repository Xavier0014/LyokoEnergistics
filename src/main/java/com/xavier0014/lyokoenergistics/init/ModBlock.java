package com.xavier0014.lyokoenergistics.init;

import com.xavier0014.lyokoenergistics.blocks.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {
	
	public static final BlockLE supercomputer = new BlockLE("supercomputer");
	public static final BlockLE materializationscanner = new BlockLE("materializationscanner");
	
	public static void init(){
		GameRegistry.registerBlock(supercomputer, "supercomputer");
		GameRegistry.registerBlock(materializationscanner, "materializationscanner");
	}

}