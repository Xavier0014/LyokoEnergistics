package com.xavier0014.lyokoenergistics.handler;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileEntityLE;


public class Upgrade extends Gui{
	
	public int x;
	public int y;
	private TileEntityLE tile;
	public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_id,"textures/gui/upgradeGui.png");
	
	public Upgrade(int x,int y,TileEntityLE tile) {
		this.x = x;
		this.y = y;
		this.tile = tile;
	}
	
	public void drawUpgrade(){
		switch (tile.corelv){
		case 1:
			this.drawTexturedModalRect(x, y, 1, 1, 42, 42);
			break;
		case 2:
			this.drawTexturedModalRect(x, y, 44, 1, 42, 42);
			break;
		case 3:
			this.drawTexturedModalRect(x, y, 87, 1, 42, 42);
			break;
		case 4:
			this.drawTexturedModalRect(x, y, 130, 1, 42, 42);
			break;
		default:
			break;
		}
		
		switch (tile.speedlv){
		case 1:
			this.drawTexturedModalRect(x + 4, y + 4, 5, 44, 16, 16);
			break;
		case 2:
			this.drawTexturedModalRect(x + 4, y + 4, 48, 44, 16, 16);
			break;
		case 3:
			this.drawTexturedModalRect(x + 4, y + 4, 91, 44, 16, 16);
			break;
		case 4:
			this.drawTexturedModalRect(x + 4, y + 4, 134, 44, 16, 16);
			break;
		default:
			break;
		}
		
		switch (tile.storagelv){
		case 1:
			this.drawTexturedModalRect(x + 22, y + 4, 23, 44, 16, 16);
			break;
		case 2:
			this.drawTexturedModalRect(x + 22, y + 4, 66, 44, 16, 16);
			break;
		case 3:
			this.drawTexturedModalRect(x + 22, y + 4, 109, 44, 16, 16);
			break;
		case 4:
			this.drawTexturedModalRect(x + 22, y + 4, 152, 44, 16, 16);
			break;
		default:
			break;
		}
		
		switch (tile.Efficiencylv){
		case 1:
			this.drawTexturedModalRect(x + 4, y + 22, 5, 62, 16, 16);
			break;
		case 2:
			this.drawTexturedModalRect(x + 4, y + 22, 48, 62, 16, 16);
			break;
		case 3:
			this.drawTexturedModalRect(x + 4, y + 22, 91, 62, 16, 16);
			break;
		case 4:
			this.drawTexturedModalRect(x + 4, y + 22, 134, 62, 16, 16);
			break;
		default:
			break;
		}

	}
	
	

}
