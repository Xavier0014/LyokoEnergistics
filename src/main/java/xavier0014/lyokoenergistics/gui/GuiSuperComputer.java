package xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import xavier0014.lyokoenergistics.handler.Upgrade;
import xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import xavier0014.lyokoenergistics.packet.PacketSuperComputer;
import xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiSuperComputer extends GuiContainer{
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_id,"textures/gui/supercomputer.png");
    private TileSuperComputerControler tilesc;
    private InventoryPlayer playerInv;
    protected int xSize = 179;
    protected int ySize = 180;
    private int k;
    private int l;
    public Upgrade upgrade;
    private Energy energy;

	public GuiSuperComputer(TileSuperComputerControler tile, InventoryPlayer inventory) {
		super(new ContainerSuperComputer(tile, inventory));
		World world = tile.getWorldObj();
		this.tilesc = tile;
	     this.playerInv = inventory;
	     this.allowUserInput = false;
	}

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
		 this.fontRendererObj.drawString(String.valueOf(tilesc.playerName), 31, this.ySize - 103, 0x95c3cb);
		if (!tilesc.onoff) {
			if(!tilesc.isMultiBlockForm){
				this.fontRendererObj.drawString("Incomplete", 31, this.ySize - 173, 0x95c3cb);
				this.fontRendererObj.drawString("structure", 31, this.ySize - 163, 0x95c3cb);
			}else if (tilesc.energyinp == null) {
				this.fontRendererObj.drawString("missing", 31, this.ySize - 173, 0x95c3cb);
				this.fontRendererObj.drawString("energy", 31, this.ySize - 163, 0x95c3cb);
				this.fontRendererObj.drawString("input", 31, this.ySize - 153, 0x95c3cb);
			}
		}else{
			this.fontRendererObj.drawString("_", 31, this.ySize - 173, 0x95c3cb);
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
        if (tilesc.isMultiBlockForm && TileSuperComputerControler.playersList.get(tilesc.playerName) <=100 && !(tilesc.energyinp == null)) {
        	buttonList.add(new InvisibleButon(0, k+114, l+8, 54, 84, ""));
		}
        upgrade = new Upgrade(k-42,l,tilesc);
        energy = new Energy(tilesc.getEnergyStored(),tilesc.getMaxEnergyStored());
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (tilesc.onoff) {
        	this.drawTexturedModalRect(k+114, l+8, 186, 7, 54, 84);
        }
        this.mc.getTextureManager().bindTexture(energy.texture);
        energy.renderEnergie(k+7, l+31);
        this.mc.getTextureManager().bindTexture(upgrade.texture);
        upgrade.drawUpgrade();
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
