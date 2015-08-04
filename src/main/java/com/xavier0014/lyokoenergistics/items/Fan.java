package com.xavier0014.lyokoenergistics.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.xavier0014.lyokoenergistics.entity.EntityFan;
import com.xavier0014.lyokoenergistics.entity.EntityLaserArrows;

public class Fan extends ItemLE{

	public Fan(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	 @Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}
	private int k;
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		 k++;
		 if (k == 10) {
			 k=0;
			  world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	          world.spawnEntityInWorld(new EntityFan(world, player));
		}
	      return itemStack;	    
	      }
}
