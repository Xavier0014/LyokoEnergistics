package com.xavier0014.lyokoenergistics.handler;


import com.xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import com.xavier0014.lyokoenergistics.gui.GuiMaterializationScanner;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileMaterializationScanner){
			return new ContainerMaterializationScanner((TileMaterializationScanner)tile, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileMaterializationScanner){
			return new GuiMaterializationScanner((TileMaterializationScanner)tile, player.inventory);
		}
		return null;
	}

}
