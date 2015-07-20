package com.xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import com.xavier0014.lyokoenergistics.handler.Upgrade;
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
    private int buttonid;
	public Upgrade upgrade;
    
	public GuiMaterializationScanner(TileMaterializationScanner tile, InventoryPlayer inventory) {
		super(new ContainerMaterializationScanner(tile, inventory));
		 this.tilems = tile;
	     this.playerInv = inventory;
	     this.allowUserInput = false;
	}
	
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
        this.fontRendererObj.drawString(this.tilems.hasCustomInventoryName() ? this.tilems.getInventoryName() : I18n.format(this.tilems.getInventoryName()), 8 + 55, 28 -26, 4210752);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName()), -10, this.ySize - 100, 4210752);
        this.fontRendererObj.drawString("Needed", 70, this.ySize - 165, 4210752);
        this.fontRendererObj.drawString("energie:", 70, this.ySize - 150, 4210752);
        this.fontRendererObj.drawString(String.valueOf(RecipesMaterializationScanner.smelting().time.get(tilems.craft)*tilems.energiemultiplier), 70, this.ySize - 140, 4210752);
        this.fontRendererObj.drawString("Matter:", 70, this.ySize - 125, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.matter), 70, this.ySize - 115, 4210752);
        this.fontRendererObj.drawString("energie:", -10, this.ySize - 150, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.getEnergyStored(null)), -10, this.ySize - 140, 4210752);
        this.fontRendererObj.drawString("Matter:", -10, this.ySize - 125, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.nomberMB), -10, this.ySize - 115, 4210752);
        this.fontRendererObj.drawString("Stored", -10, this.ySize - 165, 4210752);
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
        upgrade = new Upgrade(k-42,l,tilems);
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        buttonList.add(new GuiButton(0, k+174, l+29, 74, 20, "diamond"));//id,x,y,size x,size y, name  
        buttonList.add(new GuiButton(1, k+174, l+49, 74, 20, "iron"));
        buttonList.add(new GuiButton(2, k+174, l+69, 74, 20, "gold"));
        buttonList.add(new GuiButton(3, k+174, l+89, 74, 20, "matter ingot"));
        energie = tilems.getEnergyStored(null)*61;
    	energie = energie/tilems.getMaxEnergyStored(null);
    	this.drawTexturedModalRect(k+7, l+31, 19, 182, 17, energie);
        if (this.tilems.isProcessing()){
           progress = tilems.getWorkingTime()*24;
           progress = progress/tilems.getWorkingTimeNeeded();
           this.drawTexturedModalRect(k+80, l+27, 1, 181, 18, progress);
        }
        this.mc.getTextureManager().bindTexture(upgrade.texture);
        upgrade.drawUpgrade();
	}
	
	@Override
	 public void initGui(){
		super.initGui();
	}
	@Override
	public void actionPerformed(GuiButton button){
		LyokoEnergistics.network.sendToServer(new PacketMaterializationScanner(button.id));
		tilems.craft = button.id;
		buttonid = button.id;
	}

}
