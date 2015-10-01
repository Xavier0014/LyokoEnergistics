package xavier0014.lyokoenergistics.container;

import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileTowerConsol;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerTowerConsol extends Container{
	private final TileTowerConsol tiletc;
	
	public ContainerTowerConsol(TileTowerConsol tile,InventoryPlayer inventory) {
		this.tiletc = tile;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	public TileTowerConsol getTileTowerConsol(){
		return tiletc;
	}
}
