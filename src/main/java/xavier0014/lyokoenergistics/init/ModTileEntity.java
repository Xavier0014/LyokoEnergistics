package xavier0014.lyokoenergistics.init;

import xavier0014.lyokoenergistics.tileentity.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntity {
	
	public static void tileinit() {
		GameRegistry.registerTileEntity(TileBlockModelTest.class, "lyokoenergistics:tileBlockModelTest");
		GameRegistry.registerTileEntity(TileMaterializationScanner.class, "lyokoenergistics:tilematerializationscanner");
		GameRegistry.registerTileEntity(TileSuperComputer.class, "lyokoenergistics:TileSuperComputer");
		GameRegistry.registerTileEntity(TileSuperComputerControler.class, "lyokoenergistics:TileSuperComputerControler");
		GameRegistry.registerTileEntity(TileSuperComputerEnergyInput.class, "lyokoenergistics:TileSuperEnergyInput");
		GameRegistry.registerTileEntity(TileTowerConsol.class, "lyokoenergistics:TileTowerConsol");
		GameRegistry.registerTileEntity(TileLyokoForetMaster.class, "lyokoenergistics:TileLyokoForetMaster");
		GameRegistry.registerTileEntity(TileVirtualisationScanner.class, "lyokoenergistics:TileVirtualisationScanner");
	}

}
