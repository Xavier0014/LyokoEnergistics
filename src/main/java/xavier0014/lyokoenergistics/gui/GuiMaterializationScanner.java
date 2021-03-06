package xavier0014.lyokoenergistics.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import xavier0014.lyokoenergistics.handler.Upgrade;
import xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
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
	private int page;
	private Energy energy;
    
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
        this.fontRendererObj.drawString("energy:", 70, this.ySize - 150, 4210752);
        this.fontRendererObj.drawString(String.valueOf(RecipesMaterializationScanner.smelting().time.get(tilems.craft)*tilems.energiemultiplier), 70, this.ySize - 140, 4210752);
        this.fontRendererObj.drawString("Matter:", 70, this.ySize - 125, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.matter), 70, this.ySize - 115, 4210752);
        this.fontRendererObj.drawString("energy:", -10, this.ySize - 150, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.getEnergyStored(null)), -10, this.ySize - 140, 4210752);
        this.fontRendererObj.drawString("Matter:", -10, this.ySize - 125, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.nomberMB), -10, this.ySize - 115, 4210752);
        this.fontRendererObj.drawString("Stored", -10, this.ySize - 165, 4210752);
        this.fontRendererObj.drawString(String.valueOf(tilems.playerName), -30, this.ySize - 180, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick,int x, int y) {
		buttonList.clear();
		int progress;
		int energie;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width- this.xSize)/2;
        int l = (this.height - this.ySize)/2;
        initButton(k,l);
        upgrade = new Upgrade(k-42,l,tilems);
        energy = new Energy(tilems.getEnergyStored(null),tilems.getMaxEnergyStored(null));
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (!Loader.isModLoaded("gregtech") && !Loader.isModLoaded("ThermalFoundation")) {
        	 if (page == 0) {
        		 renderButtonList(0, 5);
        		 GuiButton buton2 = (GuiButton) buttonList.get(6);
    			 buton2.visible = true;
        	 }else if (page == 1) {
        		 renderButtonList(8, buttonList.size()-1);
        		 GuiButton buton3 = (GuiButton) buttonList.get(7);
 				 buton3.visible = true;
			}
		}else if (!Loader.isModLoaded("gregtech") && Loader.isModLoaded("ThermalFoundation")) {
			if (page == 0) {
				renderButtonList(0, 5);
				GuiButton buton2 = (GuiButton) buttonList.get(6);
				buton2.visible = true;
		    }else if (page == 1) {
		    	renderButtonList(8, 12);
				GuiButton buton2 = (GuiButton) buttonList.get(6);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(7);
				buton3.visible = true;
			}else if (page == 2) {
				renderButtonList(13, buttonList.size()-1);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
			}
		}else {
			 if (page == 0) {
				 renderButtonList(0, 5);
		            GuiButton buton3 = (GuiButton) buttonList.get(6);
					buton3.visible = true;
			}else if (page == 1) {
				renderButtonList(8, 12);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(6);
				buton3.visible = true;
			}else if (page ==2) {
				renderButtonList(13, 17);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(6);
				buton3.visible = true;
			}else if (page ==3) {
				renderButtonList(18, 22);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(6);
				buton3.visible = true;
			}else if (page ==4) {
				renderButtonList(23, 27);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(6);
				buton3.visible = true;
			}else if (page ==5) {
				renderButtonList(28, 32);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				GuiButton buton3 = (GuiButton) buttonList.get(6);
				buton3.visible = true;
			}else if (page ==6) {
				renderButtonList(33, buttonList.size()-1);
				GuiButton buton2 = (GuiButton) buttonList.get(7);
				buton2.visible = true;
				//GuiButton buton3 = (GuiButton) buttonList.get(6);
				//buton3.visible = true;
			}
		}
        
        
        if (this.tilems.isProcessing()){
           progress = tilems.getWorkingTime()*24;
           progress = progress/tilems.getWorkingTimeNeeded();
           this.drawTexturedModalRect(k+80, l+27, 1, 181, 18, progress);
        }
        this.mc.getTextureManager().bindTexture(energy.texture);
        energy.renderEnergie(k+7, l+31);
        this.mc.getTextureManager().bindTexture(upgrade.texture);
        upgrade.drawUpgrade();
	}
	
	public void renderButtonList(int x,int y) {
		for (int i = x; i <= y; i++) {
      		 GuiButton buton = (GuiButton) buttonList.get(i);
		       	 buton.visible = true;
		}
		for (int i = x; i <= y; i++) {
			 GuiButton buton = (GuiButton) buttonList.get(i);
	         buton.enabled = TileMaterializationScanner.knowledge.get(tilems.playerName).get(i);
		}
	}
	
	public void initButton(int k, int l) {
		buttonList.add(new GuiButton(0, k+174, l+29, 74, 20, "matter ingot"));//id,x,y,size x,size y, name  
        buttonList.add(new GuiButton(1, k+174, l+49, 74, 20, "iron"));
        buttonList.add(new GuiButton(2, k+174, l+69, 74, 20, "gold"));
        buttonList.add(new GuiButton(3, k+174, l+89, 74, 20, "diamond"));
        buttonList.add(new GuiButton(4, k+174, l+109, 74, 20, "lapis"));
        buttonList.add(new GuiButton(5, k+174, l+129, 74, 20, "coal"));
        buttonList.add(new GuiButton(6, k+174, l+149, 74, 20, "+"));
        
        buttonList.add(new GuiButton(7, k+174, l+29, 74, 20, "-"));
		buttonList.add(new GuiButton(8, k+174, l+49, 74, 20, "redstone"));
		buttonList.add(new GuiButton(9, k+174, l+69, 74, 20, "quartz"));
		buttonList.add(new GuiButton(10, k+174, l+89, 74, 20, "emerald"));
		if (Loader.isModLoaded("appliedenergistics2")&&!Loader.isModLoaded("gregtech")) {
			buttonList.add(new GuiButton(11, k+174, l+109, 74, 20, "certus"));
		}
		if (Loader.isModLoaded("ThermalFoundation")&&!Loader.isModLoaded("gregtech")) {
			buttonList.add(new GuiButton(12, k+174, l+129, 74, 20, "copper"));
			buttonList.add(new GuiButton(13, k+174, l+49, 74, 20, "tin"));
			buttonList.add(new GuiButton(14, k+174, l+69, 74, 20, "silver"));
			buttonList.add(new GuiButton(15, k+174, l+89, 74, 20, "lead"));
			buttonList.add(new GuiButton(16, k+174, l+109, 74, 20, "ferrous"));
		}else if (Loader.isModLoaded("gregtech")) {
			buttonList.add(new GuiButton(11, k+174, l+109, 74, 20, "certus"));
			buttonList.add(new GuiButton(12, k+174, l+129, 74, 20, "copper"));
			buttonList.add(new GuiButton(13, k+174, l+49, 74, 20, "tin"));
			buttonList.add(new GuiButton(14, k+174, l+69, 74, 20, "silver"));
			buttonList.add(new GuiButton(15, k+174, l+89, 74, 20, "lead"));
			buttonList.add(new GuiButton(16, k+174, l+109, 74, 20, "nickel"));
			buttonList.add(new GuiButton(17, k+174, l+129, 74, 20, "tungsten"));
			buttonList.add(new GuiButton(18, k+174, l+49, 74, 20, "lithium"));
			buttonList.add(new GuiButton(19, k+174, l+69, 74, 20, "aluminium"));
			buttonList.add(new GuiButton(20, k+174, l+89, 74, 20, "titanium"));
			buttonList.add(new GuiButton(21, k+174, l+109, 74, 20, "vanadium"));
			buttonList.add(new GuiButton(22, k+174, l+129, 74, 20, "chrome"));
			buttonList.add(new GuiButton(23, k+174, l+49, 74, 20, "manganese"));
			buttonList.add(new GuiButton(24, k+174, l+69, 74, 20, "cobalt"));
			buttonList.add(new GuiButton(25, k+174, l+89, 74, 20, "zinc"));
			buttonList.add(new GuiButton(26, k+174, l+109, 74, 20, "gallium"));
			buttonList.add(new GuiButton(27, k+174, l+129, 74, 20, "yttrium"));
			buttonList.add(new GuiButton(28, k+174, l+49, 74, 20, "niobium"));
			buttonList.add(new GuiButton(29, k+174, l+69, 74, 20, "antimony"));
			buttonList.add(new GuiButton(30, k+174, l+89, 74, 20, "neodymium"));
			buttonList.add(new GuiButton(31, k+174, l+109, 74, 20, "osmium"));
			buttonList.add(new GuiButton(32, k+174, l+129, 74, 20, "iridium"));
			buttonList.add(new GuiButton(33, k+174, l+49, 74, 20, "platinum"));
			buttonList.add(new GuiButton(34, k+174, l+69, 74, 20, "bismuth"));
			buttonList.add(new GuiButton(35, k+174, l+89, 74, 20, "thorium"));
		}
		for (int i = 0; i < buttonList.size(); i++) {
			GuiButton buton = (GuiButton) buttonList.get(i);
	        buton.visible = false;
		}
		
	}
	
	@Override
	 public void initGui(){
		super.initGui();
	}
	@Override
	public void actionPerformed(GuiButton button){
		buttonid = button.id;
		if (buttonid != 6 && buttonid != 7) {
			LyokoEnergistics.network.sendToServer(new PacketMaterializationScanner(button.id));
			tilems.craft = button.id;
		}
		if (buttonid == 6) {
			page++;
		}
		if (buttonid == 7) {
			page--;
		}
	}

}
