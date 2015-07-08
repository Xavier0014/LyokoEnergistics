package com.xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import com.xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import com.xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import com.xavier0014.lyokoenergistics.reference.Reference;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
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
    protected int ySize = 180;
    private int k;
    private int l;
    
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
		buttonList.clear();
		int progress;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        k = (this.width - this.xSize)/2;
        int l = (this.height - this.ySize)/2;
        buttonList.add(new GuiButton(0, k+174, l+29, 74, 20, "diamond"));//id,x,y,size x,size y, name  
        buttonList.add(new GuiButton(1, k+174, l+49, 74, 20, "iron"));
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.tilems.isProcessing()){
           progress = tilems.getWorkingTime()*24;
           progress = progress/tilems.getWorkingTimeNeeded();
           this.drawTexturedModalRect(k+80, l+27, 1, 181, 18, progress);
        }
        
	}
	
	@Override
	 public void initGui(){
		super.initGui();    
	}
	@Override
	public void actionPerformed(GuiButton button){
		LyokoEnergistics.network.sendToServer(new PacketMaterializationScanner(button.id));
		tilems.craft = button.id;
	}

}
