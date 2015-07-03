package com.xavier0014.lyokoenergistics.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.xavier0014.lyokoenergistics.init.ModItem;

public class RecipesMaterializationScanner {
	
	private static final RecipesMaterializationScanner smeltingBase = new RecipesMaterializationScanner();
	private Map smeltingList = new HashMap(); 
	
	public RecipesMaterializationScanner(){
		this.addRecipe(ModItem.matteringot, new ItemStack(Blocks.diamond_block));
	}
	
	public void addRecipe(ItemStack stack1, ItemStack stack4) {
		ItemStack[] stackList = new ItemStack[]{stack1,};
		this.smeltingList.put(stackList, stack4);
	}

        public void addRecipe(Item item1, ItemStack stack) {
		this.addRecipe(new ItemStack(item1), stack);
	}
	
	public void addRecipe(Block block1, ItemStack stack) {
		this.addRecipe(Item.getItemFromBlock(block1), stack);
	}
	
	public ItemStack getSmeltingResult(ItemStack[] stack) {
	     Iterator iterator = this.smeltingList.entrySet().iterator();
	     Entry entry;

	     do {
	         if (!iterator.hasNext()) {
	             return null; 
	         }
           entry = (Entry)iterator.next(); 
       }
       while (!this.isSameKey(stack, (ItemStack[])entry.getKey())); 
	     
       return (ItemStack)entry.getValue(); 
	  }
	
	private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
		 boolean isSame = false;
		 for(int i=0; i<=0; i++) {
			 if(stackList[i].getItem() == stackList2[i].getItem()) 
			 {
				 isSame = true; 
			 }
			 else
			 {
				 return false; 
			 }
		 }
		 return isSame;
	 }
	
	public Map getSmeltingList()
	 {
       return this.smeltingList;
    }

	public static RecipesMaterializationScanner smelting()
	{
		return smeltingBase;
	}

}
