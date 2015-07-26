package com.xavier0014.lyokoenergistics.items;

import com.xavier0014.lyokoenergistics.entity.EntityLaserArrows;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LaserArrows extends ItemLE{

	public LaserArrows(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	 @Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}
	
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
	      world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	      if (!world.isRemote)
	      {
	          world.spawnEntityInWorld(new EntityLaserArrows(world, player));
	      }
	      return itemStack;	    
	      }

}
