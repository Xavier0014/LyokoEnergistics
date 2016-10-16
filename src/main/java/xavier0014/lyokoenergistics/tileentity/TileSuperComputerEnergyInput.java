package xavier0014.lyokoenergistics.tileentity;

import java.util.ArrayList;

import xavier0014.lyokoenergistics.items.ItemLE;
import xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSuperComputerEnergyInput extends TileEntityLE implements IEnergyHandler{
	
	public TileSuperComputerControler controlerTile;
	public EnergyStorage storage = new EnergyStorage(100000,32000,0);
	
	  @Override
	    public void writeToNBT(NBTTagCompound compound){
		  super.writeToNBT(compound);
		  storage.writeToNBT(compound);
	  }
	  
	  public void readFromNBT(NBTTagCompound compound){
		  super.readFromNBT(compound);
		  storage.readFromNBT(compound);
	  }
			
			 
	
	@Override
	public void updateEntity(){
		if (controlerTile != null) {
			updateStorage();//*
		}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);//*
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
	if (controlerTile != null) {
			return storage.receiveEnergy(maxReceive, simulate);
	}
	return 0;
	}
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {
		return 0;
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from) {
		if (controlerTile != null) {
			return storage.getEnergyStored();
		}
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		if (controlerTile != null) {
			return storage.getMaxEnergyStored();
		}
		return 0;
	}
	
	
	public void updateStorage(){
		switch (controlerTile.storagelv) {
		case 0:
			storage.setCapacity(100000);
			break;
		case 1:
			storage.setCapacity(400000);
			break;
		case 2:
			storage.setCapacity(1600000);
			break;
		case 3:
			storage.setCapacity(12800000);
			break;
		case 4:
			storage.setCapacity(51200000);
			break;
		default:
			storage = new EnergyStorage(100000,32000,0);
			break;
		}
	}
	
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
	        this.readFromNBT(pkt.func_148857_g());
	        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
	    }
	  
	    public Packet getDescriptionPacket(){
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        this.writeToNBT(nbttagcompound);
	        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	    }
	
}
