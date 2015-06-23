package com.xavier0014.lyokoenergistics.items;

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
		this.setUnlocalizedName("lyokoenergistics:"+name);
		unlocalizedname = this.getUnlocalizedName();
		
	}
	
	@Override
	 @SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister iconRegister){
	     itemIcon = iconRegister.registerIcon(this.unlocalizedname.substring(this.unlocalizedname.indexOf(".") + 1));
	 }

}
