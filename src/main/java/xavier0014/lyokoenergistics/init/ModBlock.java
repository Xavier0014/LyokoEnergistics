package xavier0014.lyokoenergistics.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xavier0014.lyokoenergistics.blocks.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock {
	public static final BlockLE modeltest = new BlockModelTest("modeltest");
	public static final BlockLE supercomputer = new SuperComputer("supercomputer");
	public static final BlockLE superComputerControler = new SuperComputerControler("superComputerControler");
	public static final BlockLE superComputerCassing = new SuperComputerCassing("superComputerCassing");
	public static final BlockLE superComputerEnergyInput = new SuperComputerEnergyInput("superComputerEnergyInput");
	public static final BlockLE materializationscanner = new MaterializationScanner("materializationscanner");
	public static final VirtualisationScanner virtualisationScanner = new VirtualisationScanner(Material.iron, "virtualisationScanner");
	public static final BlockLE towerConsol = new TowerConsol("towerConsol");
	public static final BlockLE lyokoGrass = (BlockLE) new BlockLE("lyokoGrass", Material.grass).setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE lyokoSand = (BlockLE) new BlockLE("lyokoSand", Material.sand).setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE lyokoRock = (BlockLE) new BlockLE("lyokoRock", Material.grass).setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE lyokoIce = (BlockLE) new BlockLE("lyokoIce", Material.grass).setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE lyokoTree = (BlockLE) new BlockLE("lyokoTree", Material.wood).setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE lyokoForestMaster = (BlockLE) new LyokoForetMaster("lyokoForetMaster").setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE towerMaster = (BlockLE) new TowerMaster("towerMaster").setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE towerWall = (BlockLE) new TowerWall("towerWall").setBlockUnbreakable().setResistance(6000000.0F);
	public static final BlockLE towerFloor = (BlockLE) new BlockLE("towerFloor").setBlockUnbreakable().setResistance(6000000.0F);
	
	public static Block digitalSea = new BlockDigitalSea(ModFluid.DigitalSea, Material.water,"digitalSea").setResistance(6000000.0F);
	
	public static void init(){
//		GameRegistry.registerBlock(modeltest, "modeltest");
//		GameRegistry.registerBlock(supercomputer, "supercomputer");
		GameRegistry.registerBlock(superComputerControler, "superComputerControler");
		GameRegistry.registerBlock(superComputerCassing, "superComputerCassing");
		GameRegistry.registerBlock(superComputerEnergyInput, "superComputerEnergyInput");
		GameRegistry.registerBlock(materializationscanner, "materializationscanner");
		GameRegistry.registerBlock(virtualisationScanner, "virtualisationScanner");
		GameRegistry.registerBlock(towerConsol, "towerConsol");
		GameRegistry.registerBlock(lyokoGrass, "lyokoGrass");
		GameRegistry.registerBlock(lyokoSand, "lyokoSand");
		GameRegistry.registerBlock(lyokoRock, "lyokoRock");
		GameRegistry.registerBlock(lyokoIce, "lyokoIce");
		GameRegistry.registerBlock(lyokoTree, "lyokoTree");
		GameRegistry.registerBlock(lyokoForestMaster, "lyokoForestMaster");
//		GameRegistry.registerBlock(towerMaster, "towerMaster");
//		GameRegistry.registerBlock(towerWall, "towerWall");
//		GameRegistry.registerBlock(towerFloor, "towerFloor");
	}
	
	public static void initFluid() {
			GameRegistry.registerBlock(digitalSea, "digitalSea");
			ModFluid.DigitalSea.setBlock(digitalSea);
	}

}
