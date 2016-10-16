package xavier0014.lyokoenergistics.init;

import appeng.api.AEApi;
import appeng.api.definitions.Materials;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModCraftingRecipe {
	
	static Materials ae2_materials = AEApi.instance().materials();
	
	static ItemStack diament = new ItemStack(Items.diamond);
	static ItemStack matterIngot = new ItemStack(ModItem.matteringot);
	static ItemStack[] upgradematerial = new ItemStack[4];
	
	
	public static void initCrafting(){
		if (!Loader.isModLoaded("ThermalFoundation") && !Loader.isModLoaded("gregtech")) {
			initVanilaCraft();
		}else if (Loader.isModLoaded("ThermalFoundation") && !Loader.isModLoaded("gregtech")) {
			initThermalCraft();
		}else {
			initGregTechCraft();
		}
	}
	
	public static void initGregTechCraft() {
		//quantum processor
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',new ItemStack(GameRegistry.findItem("gregtech", "gt.blockmachines"),1,1640) ,'y',"plateElectrum",'z',"circuitAdvanced"));
				
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItem.UnMondeSansDanger), "zy", 'y',ModItem.quantumprocessor,'z',"record"));
						
		//materialization scanner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32670),'x',"plateSteel" ,'y',ModItem.quantumprocessor,'z',Blocks.gold_block));
		
		//supercomputer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerCassing), "ywy","zxz","ywy",'w',Blocks.obsidian,'x',"frameGtElectrum",'y',ModItem.quantumprocessor,'z',matterIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerControler), "zaz","xwy","zaz",'w',ModBlock.superComputerCassing,'x',Blocks.lever ,'y',Items.nether_star,'z',matterIngot, 'a', ModItem.quantumprocessor));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerEnergyInput), "yxy","xwx","yxy",'w',ModBlock.superComputerCassing,'x',new ItemStack(GameRegistry.findItem("gregtech", "gt.blockmachines"),1,1366) ,'y',ModItem.quantumprocessor));
						
		//recherche
		GameRegistry.addRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',Blocks.gold_block);
						
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		String[] upgradematerial = {"plateAluminium","plateStainlessSteel","plateTitanium","plateTungstenSteel"};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',"circuitMaster"));
		}
						
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		ItemStack[] motorArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32601),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32602),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32603),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32604)};
	    for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradespeed[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',motorArray[i],'z',matterIngot));
		}
						
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		ItemStack[] emitterArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32681),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32682),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32683),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32684)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradeeficiency[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',emitterArray[i],'z',matterIngot));
		}
						
		//upgrade storage
		ItemStack[] battrieArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32519),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32529),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32539),new ItemStack(GameRegistry.findItem("IC2", "itemBatLamaCrystal"),1,26)};
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',battrieArray[i],'z',matterIngot));
		}
	}
	
	
	public static void initThermalCraft(){
		
		//quantum processor
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',"ingotPlatinum",'y',Items.diamond,'z',Blocks.redstone_block));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItem.UnMondeSansDanger), "zy", 'y',ModItem.quantumprocessor,'z',"record"));
		
		//materialization scanner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',Items.ender_pearl,'x',Items.iron_ingot ,'y',ModItem.quantumprocessor,'z',"blockElectrum"));
		
		//supercomputer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerCassing), "ywy","zxz","ywy",'w',Blocks.obsidian,'x',"blockElectrum",'y',ModItem.quantumprocessor,'z',matterIngot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerControler), "zaz","xwy","zaz",'w',ModBlock.superComputerCassing,'x',Blocks.lever ,'y',Items.nether_star,'z',matterIngot, 'a',"dustCryotheum"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.superComputerEnergyInput), "yxy","xwx","yxy",'w',ModBlock.superComputerCassing,'x',"blockCopper" ,'y',ModItem.quantumprocessor));
		
		//recherche
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',"blockEnderium"));
						
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		String[] upgradematerial = {"ingotLead","ingotInvar", "ingotElectrum","ingotEnderium"};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',Items.nether_star));
		}
						
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradespeed[i], "xwx","wzw","xwx",'x',upgradematerial[i] ,'z',matterIngot,'w',"gearBronze"));
		}
						
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradeeficiency[i], "xwx","yzy","xyx",'x',upgradematerial[i] ,'y',Items.gold_ingot,'z',matterIngot,'w',"gearSilver"));
		}
						
		//upgrade speed
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Blocks.redstone_block,'z',matterIngot));
		}
	}
	
	public static void initVanilaCraft(){
		

		//quantum processor
		GameRegistry.addRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',diament ,'y',Items.redstone,'z',Items.emerald);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItem.UnMondeSansDanger), "zy", 'y',ModItem.quantumprocessor,'z',"record"));
				
		//materialization scanner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',Items.ender_pearl,'x',"ingotIron" ,'y',ModItem.quantumprocessor,'z',Blocks.gold_block));
		
		
		//supercomputer
		GameRegistry.addRecipe(new ItemStack(ModBlock.superComputerCassing), "ywy","zxz","ywy",'w',Blocks.obsidian,'x',Blocks.gold_block ,'y',ModItem.quantumprocessor,'z',matterIngot);
		GameRegistry.addRecipe(new ItemStack(ModBlock.superComputerControler), "zaz","xwy","zaz",'w',ModBlock.superComputerCassing,'x',Blocks.lever ,'y',Items.nether_star,'z',matterIngot, 'a', ModItem.quantumprocessor);
		GameRegistry.addRecipe(new ItemStack(ModBlock.superComputerEnergyInput), "yxy","xwx","yxy",'w',ModBlock.superComputerCassing,'x',Blocks.redstone_block ,'y',ModItem.quantumprocessor);
				
		//recherche
		GameRegistry.addRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',Blocks.gold_block);
				
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		ItemStack[] upgradematerial = {new ItemStack(Items.iron_ingot),new ItemStack(Items.gold_ingot),new ItemStack(Items.diamond),new ItemStack(ModItem.quantumprocessor)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',Items.nether_star);
		}
				
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradespeed[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Items.sugar,'z',matterIngot);
		}
				
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradeeficiency[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Items.gold_ingot,'z',matterIngot);
		}
				
		//upgrade speed
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Blocks.redstone_block,'z',matterIngot);
		}
	}
}
