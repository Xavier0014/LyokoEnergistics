package xavier0014.lyokoenergistics.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xavier0014.lyokoenergistics.blocks.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {
	
	public static final BlockLE supercomputer = new SuperComputer("supercomputer");
	public static final BlockLE materializationscanner = new MaterializationScanner("materializationscanner");
	public static final BlockLE virtualisationScanner = new VirtualisationScanner("virtualisationScanner");
	public static final BlockLE towerConsol = new TowerConsol("towerConsol");
	public static final BlockLE lyokoGrass = new BlockLE("lyokoGrass");
	public static final BlockLE lyokoTree = new BlockLE("lyokoTree");
	
	public static Block digitalSea = new BlockDigitalSea(ModFluid.DigitalSea, Material.water,"digitalSea");
	
	public static void init(){
		GameRegistry.registerBlock(supercomputer, "supercomputer");
		GameRegistry.registerBlock(materializationscanner, "materializationscanner");
		GameRegistry.registerBlock(virtualisationScanner, "virtualisationScanner");
		GameRegistry.registerBlock(towerConsol, "towerConsol");
		GameRegistry.registerBlock(lyokoGrass, "lyokoGrass");
		GameRegistry.registerBlock(lyokoTree, "lyokoTree");
	}
	
	public static void initFluid() {
			GameRegistry.registerBlock(digitalSea, "digitalSea");
			ModFluid.DigitalSea.setBlock(digitalSea);
	}

}
