package xavier0014.lyokoenergistics.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public RecipesMaterializationScanner(){
		this.addRecipe(new ItemStack(Items.diamond),0,1000000,8192);
		this.addRecipe(new ItemStack(Items.iron_ingot),1,50000,256);
		this.addRecipe(new ItemStack(Items.gold_ingot),2,500000,2048);
		this.addRecipe(new ItemStack(ModItem.matteringot),3,250000,500);
		this.addRecipe(new ItemStack(Items.dye, 1, 4),4,100000,864);
		this.addRecipe(new ItemStack(Items.coal),5,500000,32);
		this.addRecipe(null,6,0,0);
		this.addRecipe(null,7,0,0);
		this.addRecipe(new ItemStack(Items.redstone),8,750000,32);
		this.addRecipe(new ItemStack(Items.quartz),9,250000,256);
		this.result.add(null);
	}
	
	public void addRecipe(ItemStack stack4,int id, int energie,int matter) {
		this.result.add(id,stack4);
		this.time.add(id, energie);
		this.matter.add(matter);
	}

	public static RecipesMaterializationScanner smelting(){
		return smeltingBase;
	}

}
