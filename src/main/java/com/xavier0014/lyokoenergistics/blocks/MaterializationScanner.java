package com.xavier0014.lyokoenergistics.blocks;

import com.xavier0014.lyokoenergistics.proxy.ClientProxy;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MaterializationScanner extends BlockLE{

	public MaterializationScanner(String name) {
		super(name);
	}
	
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	 
	 public boolean renderAsNormalBlock(){
	    return false;
	 }
	 
	 public boolean isOpaqueCube(){
	     return false;
	 }
	    
	 public int getRenderType(){
	     return ClientProxy.tesrRenderId;
	 }
	 
	@Override
	public TileEntity createTileEntity(World world, int metadata){
	    return new TileMaterializationScanner();
	}
	

}
