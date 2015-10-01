package xavier0014.lyokoenergistics.init;

import xavier0014.lyokoenergistics.blocks.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {
	
	public static final BlockLE supercomputer = new SuperComputer("supercomputer");
	public static final BlockLE materializationscanner = new MaterializationScanner("materializationscanner");
	public static final BlockLE virtualisationScanner = new VirtualisationScanner("virtualisationScanner");
	public static final BlockLE towerConsol = new TowerConsol("towerConsol");
	
	public static void init(){
		GameRegistry.registerBlock(supercomputer, "supercomputer");
		GameRegistry.registerBlock(materializationscanner, "materializationscanner");
		GameRegistry.registerBlock(virtualisationScanner, "virtualisationScanner");
		GameRegistry.registerBlock(towerConsol, "towerConsol");
	}

}
