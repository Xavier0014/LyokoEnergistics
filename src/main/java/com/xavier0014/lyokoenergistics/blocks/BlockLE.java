package com.xavier0014.lyokoenergistics.blocks;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.creativetabs.LECreativeTabs;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockLE extends Block{
	
	private String name;

	public BlockLE(String name) {
		super(Material.iron);
		this.name = name;
		setBlockName(Reference.MOD_id +":"+name);
		this.setCreativeTab(LyokoEnergistics.lyokotab);
	}
	
	@Override
	 @SideOnly(Side.CLIENT)
	 public void registerBlockIcons(IIconRegister iconRegister){
	     blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	 }
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
	     return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	 }

}
