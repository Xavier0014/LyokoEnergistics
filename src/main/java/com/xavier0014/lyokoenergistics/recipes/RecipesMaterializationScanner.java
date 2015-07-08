package com.xavier0014.lyokoenergistics.recipes;

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

import com.xavier0014.lyokoenergistics.init.ModItem;

public class RecipesMaterializationScanner {
	
	private static final RecipesMaterializationScanner smeltingBase = new RecipesMaterializationScanner();
	public ArrayList<ItemStack> result = new ArrayList<ItemStack>();
	public ArrayList<Integer> time = new ArrayList<Integer>();
	public ArrayList<ItemStack> input = new ArrayList<ItemStack>();
	
	public RecipesMaterializationScanner(){
		this.addRecipe(ModItem.matteringot, new ItemStack(Blocks.diamond_block),0,80);
		this.addRecipe(ModItem.matteringot, new ItemStack(Items.iron_ingot),1,40);
		this.result.add(null);
	}
	
	public void addRecipe(ItemStack stack1, ItemStack stack4,int id, int time) {
		ItemStack[] stackList = new ItemStack[]{stack1,};
		this.result.add(id,stack4);
		this.input.add(id,stack1);
		
		this.time.add(id, time);
	}

    public void addRecipe(Item item1, ItemStack stack,int id,int time) {
		this.addRecipe(new ItemStack(item1), stack,id, time);
	}
	
	public void addRecipe(Block block1, ItemStack stack,int id,int time) {
		this.addRecipe(Item.getItemFromBlock(block1), stack,id, time);
	}

	public static RecipesMaterializationScanner smelting()
	{
		return smeltingBase;
	}

}
