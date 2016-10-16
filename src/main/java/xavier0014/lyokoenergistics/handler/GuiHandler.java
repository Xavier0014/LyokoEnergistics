package xavier0014.lyokoenergistics.handler;


import xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import xavier0014.lyokoenergistics.container.ContainerTowerConsol;
import xavier0014.lyokoenergistics.gui.GuiMaterializationScanner;
import xavier0014.lyokoenergistics.gui.GuiSuperComputer;
import xavier0014.lyokoenergistics.gui.GuiTowerConsol;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import xavier0014.lyokoenergistics.tileentity.TileTowerConsol;
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
		if(tile instanceof TileSuperComputerControler){
			return new ContainerSuperComputer((TileSuperComputerControler)tile, player.inventory);
		}
		if(tile instanceof TileTowerConsol){
			return new ContainerTowerConsol((TileTowerConsol) tile, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileMaterializationScanner){
			return new GuiMaterializationScanner((TileMaterializationScanner)tile, player.inventory);
		}
		if(tile instanceof TileSuperComputerControler){
			return new GuiSuperComputer((TileSuperComputerControler) tile, player.inventory);
		}
		if(tile instanceof TileTowerConsol){
			return new GuiTowerConsol((TileTowerConsol) tile, player.inventory);
		}
		return null;
	}

}
