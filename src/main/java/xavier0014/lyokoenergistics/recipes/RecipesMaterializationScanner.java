package xavier0014.lyokoenergistics.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import appeng.api.AEApi;
import appeng.api.definitions.Materials;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import scala.annotation.meta.setter;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xavier0014.lyokoenergistics.init.ModItem;

public class RecipesMaterializationScanner {
	
	private static final RecipesMaterializationScanner smeltingBase = new RecipesMaterializationScanner();
	public ArrayList<ItemStack> result = new ArrayList<ItemStack>();
	public ArrayList<Integer> time = new ArrayList<Integer>();
	public ArrayList<Integer> matter = new ArrayList<Integer>();
	Materials ae2_materials = AEApi.instance().materials();
	
	public RecipesMaterializationScanner(){
		this.addRecipe(new ItemStack(ModItem.matteringot),0,250000,320);
		this.addRecipe(new ItemStack(Items.iron_ingot),1,50000,256);
		this.addRecipe(new ItemStack(Items.gold_ingot),2,500000,2048);
		this.addRecipe(new ItemStack(Items.diamond),3,1000000,8192);
		this.addRecipe(new ItemStack(Items.dye, 1, 4),4,150000,864);
		this.addRecipe(new ItemStack(Items.coal),5,500000,32);
		this.addRecipe(null,6,0,0);
		this.addRecipe(null,7,0,0);
		this.addRecipe(new ItemStack(Items.redstone),8,750000,32);
		this.addRecipe(new ItemStack(Items.quartz),9,250000,256);
		this.addRecipe(new ItemStack(ae2_materials.materialCertusQuartzCrystal.item()),10,400000,256);
		this.addRecipe(new ItemStack(Items.emerald),11,1500000,16384);
		if (Loader.isModLoaded("ThermalFoundation")&&!Loader.isModLoaded("gregtech")) {
			this.addRecipe(new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,64),12,150000,85);
			this.addRecipe(new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,65),13,350000,256);
			this.addRecipe(new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,66),14,475000,1024);
			this.addRecipe(new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,67),15,400000,512);
			this.addRecipe(new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,68),16,450000,1024);
		}
		if (Loader.isModLoaded("gregtech")) {
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11035),12,150000,85);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11037),13,200000,256);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11054),14,475000,1024);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11089),15,400000,512);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11034),16,450000,1024);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11081),17,3500000,24588);
			
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2006),18,1000000,128);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2019),19,750000,4096);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2028),20,3000000,16384);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2029),21,2500000,12292);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2030),22,2500000,16384);
			
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11031),23,1000000,8192);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11033),24,250000,512);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11036),25,100000,256);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2037),26,800000,4096);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2037),27,850000,5120);
			
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2047),28,950000,6144);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11058),29,550000,1024);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,2067),30,650000,2048);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11083),31,50000000,32784);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11084),32,1500000,16392);
			
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11085),33,1750000,16392);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11090),34,450000,1024);
			this.addRecipe(new ItemStack(GameRegistry.findItem("gregtech", "gt.metaitem.01"),1,11096),35,1000000,512);
		}
		this.result.add(null);
	}
	  //ItemStack copper = new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"),1,64);
	  //GameRegistry.addRecipe(copper, "xyx","yzy","xyx",'x',copper ,'y',copper,'z',matterIngot);
	public void addRecipe(ItemStack stack4,int id, int energie,int matter) {
		this.result.add(id,stack4);
		this.time.add(id, energie);
		this.matter.add(id,matter);
	}

	public static RecipesMaterializationScanner smelting(){
		return smeltingBase;
	}

}