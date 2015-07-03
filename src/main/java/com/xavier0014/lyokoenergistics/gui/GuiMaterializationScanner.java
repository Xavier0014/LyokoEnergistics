package com.xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMaterializationScanner extends GuiContainer{
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_id,"textures/gui/materializationscanner.png");
    private TileMaterializationScanner tilems;
    private InventoryPlayer playerInv;
    protected int xSize = 254;
    protected int ySize = 181;

	public GuiMaterializationScanner(TileMaterializationScanner tile, InventoryPlayer inventory) {
		super(new ContainerMaterializationScanner(tile, inventory));
		 this.tilems = tile;
	     this.playerInv = inventory;
	     this.allowUserInput = false;
	}
	
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
        this.fontRendererObj.drawString(this.tilems.hasCustomInventoryName() ? this.tilems.getInventoryName() : I18n.format(this.tilems.getInventoryName()), 8 + 55, 28 -26, 4210752);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName()), -10, this.ySize - 105, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick,int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize)/2;
        int l = (this.height - this.ySize)/2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	 public void initGui(){
	        super.initGui();
	        this.fontRendererObj.drawString(this.tilems.storedenergie, -10, this.ySize - 120, 4210752);
	 }

}
