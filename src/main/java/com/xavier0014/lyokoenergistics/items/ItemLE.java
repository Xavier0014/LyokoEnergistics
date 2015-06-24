package com.xavier0014.lyokoenergistics.items;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemLE extends Item{
	
	private String name;
	private String unlocalizedname;
	
	public ItemLE(String name) {
		super();
		this.name = name;
		this.setUnlocalizedName(Reference.MOD_id +":"+name);
		unlocalizedname = this.getUnlocalizedName();
		this.setCreativeTab(LyokoEnergistics.lyokotab);
	}
	
	@Override
	 @SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister iconRegister){
	     itemIcon = iconRegister.registerIcon(this.unlocalizedname.substring(this.unlocalizedname.indexOf(".") + 1));
	 }
	
}
