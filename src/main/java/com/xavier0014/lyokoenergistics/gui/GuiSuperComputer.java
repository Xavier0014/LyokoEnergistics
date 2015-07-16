package com.xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import com.xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import com.xavier0014.lyokoenergistics.packet.PacketSuperComputer;
import com.xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import com.xavier0014.lyokoenergistics.tileentity.TileSuperComputer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiSuperComputer extends GuiContainer{
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_id,"textures/gui/supercomputer.png");
    private TileSuperComputer tilesc;
    private InventoryPlayer playerInv;
    protected int xSize = 179;
    protected int ySize = 180;
    private int k;
    private int l;

	public GuiSuperComputer(TileSuperComputer tile, InventoryPlayer inventory) {
		super(new ContainerSuperComputer(tile, inventory));
		this.tilesc = tile;
	     this.playerInv = inventory;
	     this.allowUserInput = false;
	}

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
		if (tilesc.onoff) {
			
		}else{
			this.fontRendererObj.drawString("_", 30, this.ySize - 178, 16777215);
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick,int x, int y) {
		buttonList.clear();
		int progress;
		int energie;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize)/2;
        int l = (this.height - this.ySize)/2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        buttonList.add(new InvisibleButon(0, k+114, l+8, 54, 84, ""));
        if (tilesc.onoff) {
        	this.drawTexturedModalRect(k+114, l+8, 186, 7, 54, 84);
        }
        //energie = tilesc.getEnergyStored(null)*61;
    	//energie = energie/tilesc.getMaxEnergyStored(null);
    	//this.drawTexturedModalRect(k+7, l+31, 19, 182, 17, energie);
	}
	
	@Override
	 public void initGui(){
		super.initGui();
	}
	@Override
	public void actionPerformed(GuiButton button){
		switch (button.id) {
		case 0:
			if (tilesc.onoff) {
				tilesc.onoff = false;
				LyokoEnergistics.network.sendToServer(new PacketSuperComputer(false));
			}else {
				tilesc.onoff = true;
				LyokoEnergistics.network.sendToServer(new PacketSuperComputer(true));
			}
			break;

		default:
			break;
		}
	}

}
