package xavier0014.lyokoenergistics.init;

import appeng.api.AEApi;
import appeng.api.definitions.Materials;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModCraftingRecipe {
	
	static Materials ae2_materials = AEApi.instance().materials();
	
	static ItemStack fluix = new ItemStack(ae2_materials.materialFluixCrystal.item(),1,7);
	static ItemStack singlarity = new ItemStack(ae2_materials.materialSingularity.item(),1,47);
	static ItemStack Processor = new ItemStack(ae2_materials.materialEngProcessor.item(),1,24);
	static ItemStack Processorlogi = new ItemStack(ae2_materials.materialLogicProcessor.item(),1,22);
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
		GameRegistry.addRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',new ItemStack(GameRegistry.findItem("gregtech", "gt.blockmachines"),1,1640) ,'y',fluix,'z',new ItemStack(GameRegistry.findItem("IC2", "itemPartCircuitAdv")));
				
		GameRegistry.addRecipe(new ItemStack(ModItem.UnMondeSansDanger), "yyy","yzy","yyy",'y',new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17020),'z',singlarity);
						
		//materialization scanner
		GameRegistry.addRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32670),'x',new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17305) ,'y',ModItem.quantumprocessor,'z',singlarity);
		if (Loader.isModLoaded("Railcraft")) {
			GameRegistry.addRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32670),'x',new ItemStack(GameRegistry.findItem("Railcraft", "part.plate"),1,1) ,'y',ModItem.quantumprocessor,'z',singlarity);
		}
		
						
		//recherche
		GameRegistry.addRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',singlarity);
						
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		ItemStack[] upgradematerial = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17019),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17306),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17028),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,17316)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',singlarity);
		}
						
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		ItemStack[] motorArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32601),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32602),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32603),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32604)};
	    for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradespeed[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',motorArray[i],'z',matterIngot);
		}
						
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		ItemStack[] emitterArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32681),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32682),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32683),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32684)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradeeficiency[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',emitterArray[i],'z',matterIngot);
		}
						
		//upgrade storage
		ItemStack[] battrieArray = {new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32519),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32529),new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,32539),new ItemStack(GameRegistry.findItem("IC2", "itemBatLamaCrystal"),1,26)};
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',battrieArray[i],'z',matterIngot);
		}
	}
	
	
	public static void initThermalCraft(){
		
		//quantum processor
		GameRegistry.addRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,69),'y',fluix,'z',Processor);
		
		GameRegistry.addRecipe(new ItemStack(ModItem.UnMondeSansDanger), "yyy","yzy","yyy",'y',new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,66),'z',singlarity);
		
		//materialization scanner
		GameRegistry.addRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',Items.ender_pearl,'x',Items.iron_ingot ,'y',ModItem.quantumprocessor,'z',singlarity);
						
		//recherche
		GameRegistry.addRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',singlarity);
						
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		ItemStack[] upgradematerial = {new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,67),new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,72),new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,71),new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,76)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',singlarity);
		}
						
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradespeed[i], "xwx","wzw","xwx",'x',upgradematerial[i] ,'z',matterIngot,'w',new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,137));
		}
						
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradeeficiency[i], "xwx","yzy","xyx",'x',upgradematerial[i] ,'y',Processorlogi,'z',matterIngot,'w',new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,130));
		}
						
		//upgrade speed
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Blocks.redstone_block,'z',matterIngot);
		}
	}
	
	public static void initVanilaCraft(){
		//quantum processor
		GameRegistry.addRecipe(new ItemStack(ModItem.quantumprocessor), "xyx","yzy","xyx",'x',diament ,'y',fluix,'z',Processor);
		
		GameRegistry.addRecipe(new ItemStack(ModItem.UnMondeSansDanger), "yyy","yzy","yyy",'y',new ItemStack(ae2_materials.materialLogicProcessor.item(),1,5),'z',singlarity);
				
		//materialization scanner
		GameRegistry.addRecipe(new ItemStack(ModBlock.materializationscanner), "xwx","yzy","xwx",'w',Items.ender_pearl,'x',Items.iron_ingot ,'y',ModItem.quantumprocessor,'z',singlarity);
				
		//recherche
		GameRegistry.addRecipe(new ItemStack(ModBlock.towerConsol), "xyx","yzy","xyx",'x',matterIngot ,'y',ModItem.quantumprocessor,'z',singlarity);
				
		//upgrade core
		ItemStack[] upgradecore = {new ItemStack(ModItem.basiccore),new ItemStack(ModItem.hardenedcore),new ItemStack(ModItem.reinforcedcore),new ItemStack(ModItem.resonantcore)};
		ItemStack[] upgradematerial = {new ItemStack(Items.iron_ingot),new ItemStack(Items.gold_ingot),new ItemStack(Items.diamond),new ItemStack(ModItem.quantumprocessor)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradecore[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',matterIngot,'z',singlarity);
		}
				
		//upgrade speed
		ItemStack[] upgradespeed = {new ItemStack(ModItem.basicspeed),new ItemStack(ModItem.hardenedspeed),new ItemStack(ModItem.reinforcedspeed),new ItemStack(ModItem.resonantspeed)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradespeed[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Items.sugar,'z',matterIngot);
		}
				
		//upgrade eficiency
		ItemStack[] upgradeeficiency = {new ItemStack(ModItem.basicefficacite),new ItemStack(ModItem.hardenedefficacite),new ItemStack(ModItem.reinforcedefficacite),new ItemStack(ModItem.resonantefficacite)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradeeficiency[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Processorlogi,'z',matterIngot);
		}
				
		//upgrade speed
		ItemStack[] upgradestorage = {new ItemStack(ModItem.basicstorage),new ItemStack(ModItem.hardenedstorage),new ItemStack(ModItem.reinforcedstorage),new ItemStack(ModItem.resonantstorage)};
		for (int i = 0; i < upgradematerial.length; i++) {
			GameRegistry.addRecipe(upgradestorage[i], "xyx","yzy","xyx",'x',upgradematerial[i] ,'y',Blocks.redstone_block,'z',matterIngot);
		}
	}
}
