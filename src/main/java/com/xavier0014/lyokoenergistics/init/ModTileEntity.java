package com.xavier0014.lyokoenergistics.init;

import com.xavier0014.lyokoenergistics.tileentity.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntity {
	
	public static void tileinit() {
		GameRegistry.registerTileEntity(TileMaterializationScanner.class, "lyokoenergistics:tilematerializationscanner");
		GameRegistry.registerTileEntity(TileSuperComputer.class, "lyokoenergistics:TileSuperComputer");
	}

}
