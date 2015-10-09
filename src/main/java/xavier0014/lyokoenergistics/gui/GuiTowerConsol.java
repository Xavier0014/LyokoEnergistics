package xavier0014.lyokoenergistics.gui;

import org.lwjgl.opengl.GL11;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import xavier0014.lyokoenergistics.container.ContainerTowerConsol;
import xavier0014.lyokoenergistics.packet.PacketMaterializationScanner;
import xavier0014.lyokoenergistics.packet.PacketSuperComputer;
import xavier0014.lyokoenergistics.packet.PacketTowerConsol;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileTowerConsol;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GuiTowerConsol extends GuiContainer{
	
	private TileTowerConsol tiletc;
	
	public GuiTowerConsol(Container p_i1072_1_) {
		super(p_i1072_1_);
	}

	public GuiTowerConsol(TileTowerConsol tile, InventoryPlayer inventory) {
		super(new ContainerTowerConsol(tile, inventory));
		this.tiletc = tile;
	}
	
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_){
		if (tiletc.menu == 5) {
			this.fontRendererObj.drawString("processing "+ tiletc.progresse +"%", 30, this.ySize - 178, 16777215);
		}if (tiletc.menu == 6) {
			this.fontRendererObj.drawString(tiletc.result.get(tiletc.randomInt).getDisplayName(), 30, this.ySize - 178, 16777215);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick,int x, int y) {
		 buttonList.clear();
		 int k = (this.width - this.xSize)/2;
	     int l = (this.height - this.ySize)/2;
	     if (tiletc.menu == 0) {
	    	 buttonList.add(new GuiButton(0, k+74, l+29, 74, 20, "forest"));
	    	 buttonList.add(new GuiButton(1, k+74, l+49, 74, 20, "desert"));
	    	 buttonList.add(new GuiButton(2, k+74, l+69, 74, 20, "banquise"));
	    	 buttonList.add(new GuiButton(3, k+74, l+89, 74, 20, "montagne"));
		}else if (tiletc.menu == 1) {
			 buttonList.add(new GuiButton(4, k, l+29, 74, 20, "tower 1"));
	    	 buttonList.add(new GuiButton(5, k, l+49, 74, 20, "tower 2"));
	    	 buttonList.add(new GuiButton(6, k, l+69, 74, 20, "tower 3"));
	    	 buttonList.add(new GuiButton(7, k, l+89, 74, 20, "tower 4"));
	    	 buttonList.add(new GuiButton(8, k, l+109, 74, 20, "tower 5"));
	    	 buttonList.add(new GuiButton(9, k+74, l+29, 74, 20, "tower 6"));
	    	 buttonList.add(new GuiButton(10, k+74, l+49, 74, 20, "tower 7"));
	    	 buttonList.add(new GuiButton(11, k+74, l+69, 74, 20, "tower 8"));
	    	 buttonList.add(new GuiButton(12, k+74, l+89, 74, 20, "tower 9"));
	    	 buttonList.add(new GuiButton(13, k+74, l+109, 74, 20, "tower 10"));
	    	 buttonList.add(new GuiButton(14, k+74, l+129, 74, 20, "back"));
		}else if (tiletc.menu == 6) {
			 buttonList.add(new GuiButton(14, k+74, l+129, 74, 20, "back"));
		}
		
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		if (tiletc.menu == 0) {
			switch (button.id) {
			case 0:
				tiletc.menu = 1;
				break;
			case 1:
				tiletc.menu = 1;
				break;
			case 2:
				tiletc.menu = 1;
				break;
			case 3:
				tiletc.menu = 1;
				break;
			default:
				break;
			}
		}else if (tiletc.menu == 1) {
			switch (button.id) {
			case 4:
				tiletc.towerid=1;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(1));
				tiletc.menu = 5;
				break;
			case 5:
				tiletc.towerid=2;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(2));
				tiletc.menu = 5;
				break;
			case 6:
				tiletc.towerid=3;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(3));
				tiletc.menu = 5;
				break;
			case 7:
				tiletc.towerid=4;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(4));
				tiletc.menu = 5;
				break;
			case 8:
				tiletc.towerid=5;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(5));
				tiletc.menu = 5;
				break;
			case 9:
				tiletc.towerid=6;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(6));
				tiletc.menu = 5;
				break;
			case 10:
				tiletc.towerid=7;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(7));
				tiletc.menu = 5;
				break;
			case 11:
				tiletc.towerid=8;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(8));
				tiletc.menu = 5;
				break;
			case 12:
				tiletc.towerid=9;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(9));
				tiletc.menu = 5;
				break;
			case 13:
				tiletc.towerid=10;
				LyokoEnergistics.network.sendToServer(new PacketTowerConsol(10));
				tiletc.menu = 5;
				break;
			default:
				tiletc.menu = 0;
				break;
			}
		}else if (tiletc.menu == 6) {
			tiletc.menu = 0;
		}
		
	}
}
