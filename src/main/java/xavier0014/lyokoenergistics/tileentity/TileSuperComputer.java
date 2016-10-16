package xavier0014.lyokoenergistics.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import xavier0014.lyokoenergistics.init.ModItem;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileSuperComputer extends TileEntityLE implements IEnergyHandler, IInventory{
	
	private boolean hasMaster, isMaster;
    public int masterX, masterY, masterZ;
    public boolean onoff;
    public TileSuperComputer mastertile;
//    public static HashMap<String, ArrayList<Boolean>> playersList = new HashMap<String, ArrayList<Boolean>>();
    public static final Boolean[] template = {false};
    
    
    
    public TileSuperComputer(){
    	
    }
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
       
        compound.setBoolean("hasMaster", hasMaster);
        compound.setBoolean("isMaster", isMaster);
        compound.setInteger("masterX", masterX);
        compound.setInteger("masterY", masterY);
        compound.setInteger("masterZ", masterZ);
        if (hasMaster() && isMaster()) {
        	compound.setBoolean("onoff", onoff);
//        	savePlayerData(compound);
        	storage.writeToNBT(compound);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
       
        hasMaster = compound.getBoolean("hasMaster");
        isMaster = compound.getBoolean("isMaster");
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
        if (hasMaster() && isMaster()) {
        	onoff = compound.getBoolean("onoff");
//        	readPlayerData(compound);
        }
        storage.readFromNBT(compound);
    }
    
//    public void savePlayerData(NBTTagCompound compound) {
//    	try {
//    		int k = 0;
//    		for (String i : playersList.keySet()) {
//    		    ArrayList<Boolean> playerKnow = playersList.get(i);
//    		    for (int j = 0; j < playerKnow.size(); j++) {
//    		    	compound.setBoolean("knowledge"+i+j, playerKnow.get(j));
//    			}
//    		    compound.setString("pName"+k, i);
//    		    k++;
//    		    compound.setInteger("playerKownSize", playerKnow.size());
//    		}
//    		compound.setInteger("kownSize", playersList.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	} 
//	
//	public void readPlayerData(NBTTagCompound compound) {
//		ArrayList<String> playerList = new ArrayList<String>(); 
//		for (int k = 0; k < compound.getInteger("kownSize"); k++) {
//			playerList.add(compound.getString("pName"+k));
//		}
//		for (String i : playerList) {
//			ArrayList<Boolean> l = new ArrayList<Boolean>();
//			for (int j = 0; j < compound.getInteger("playerKownSize"); j++) {
//				l.add(compound.getBoolean("knowledge"+i+j));
//			}
//			playersList.put(i, l);
//		}
//	}
	
	
	@Override
	public void updateEntity() {
//		if (onoff) {
//    		storage.setEnergyStored(storage.getEnergyStored()-100);
//    		try {
//    			playersList.get(playerName).add(0, true);//is on
//    			System.out.println(Boolean.toString(playersList.get(playerName).get(0))+this.playerName + storage.getEnergyStored());
//			} catch (NullPointerException e) {
//				TileSuperComputer.playersList.put(this.playerName, new ArrayList<Boolean>(Arrays.asList(template)));
//				System.err.println("err");
//			}
//    	}else {
//    		try {
//    			playersList.get(playerName).add(0, false);//is on
//			} catch (NullPointerException e) {
//				TileSuperComputer.playersList.put(this.playerName, new ArrayList<Boolean>(Arrays.asList(template)));
//				System.err.println("err");
//			}
//		}
		if (storage.getEnergyStored() <= 0) {
			onoff = false;
		}
		upgradeSlotUpdate();
		updateValue();
        if (hasMaster()) { 
              if (isMaster()) {
            	  worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            	  storage.setCapacity(100000);
              }
        } else {
              if (checkMultiBlockForm())
                  setupStructure();
        }
    }
	
	public void updateValue() {
		if (mastertile != null &&!hasMaster && !isMaster) {
			this.onoff = mastertile.onoff;
		}
	}
	
	
	//--------------------- energy -------------------------
	
	public EnergyStorage storage = new EnergyStorage(100000,32000,0);

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		System.out.printf("%s%s\n", worldObj.isRemote, storage.getEnergyStored());
			return storage.receiveEnergy(maxReceive, simulate);
	}
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {
		return 0;
	}
	//LyokoEnergistics.network.sendToAll(new PacketSuperComputer2(storage.getEnergyStored()));
	@Override
	public int getEnergyStored(ForgeDirection from) {
		System.out.println(storage.getEnergyStored());
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
			return storage.getMaxEnergyStored();
	}
	
	//----------------------- multiblock -----------------------
	
	public boolean checkMultiBlockForm() {
		int i = 0;
		for (int x = xCoord - 1; x < xCoord + 2; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if (tile != null && (tile instanceof TileSuperComputer)) {
						if (this.isMaster()) {
							if (((TileSuperComputer)tile).hasMaster())
								i++;
						} else if (!((TileSuperComputer)tile).hasMaster())
							i++;
					}
			}
		
		return i > 10 && worldObj.isAirBlock(xCoord + 1, yCoord + 1, zCoord +1) && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord +1) && worldObj.isAirBlock(xCoord-1, yCoord + 1, zCoord +1)&& worldObj.isAirBlock(xCoord+1, yCoord + 1, zCoord)&& worldObj.isAirBlock(xCoord-1, yCoord + 1, zCoord)&& worldObj.isAirBlock(xCoord, yCoord + 1, zCoord -1)&& worldObj.isAirBlock(xCoord -1, yCoord + 1, zCoord -1)&& worldObj.isAirBlock(xCoord+1, yCoord + 1, zCoord -1)
				&& worldObj.isAirBlock(xCoord + 1, yCoord + 2, zCoord +1) && worldObj.isAirBlock(xCoord, yCoord + 2, zCoord +1) && worldObj.isAirBlock(xCoord-1, yCoord + 2, zCoord +1)&& worldObj.isAirBlock(xCoord+1, yCoord + 2, zCoord)&& worldObj.isAirBlock(xCoord-1, yCoord + 2, zCoord)&& worldObj.isAirBlock(xCoord, yCoord + 2, zCoord -1)&& worldObj.isAirBlock(xCoord -1, yCoord + 2, zCoord -1)&& worldObj.isAirBlock(xCoord+1, yCoord + 2, zCoord -1);
	}
 
    public void setupStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    if (tile != null && (tile instanceof TileSuperComputer)) {
                        ((TileSuperComputer) tile).setMasterCoords(xCoord, yCoord, zCoord);
                        ((TileSuperComputer) tile).setHasMaster(true);
                        ((TileSuperComputer) tile).setIsMaster(master);
                    }
                }
    }
 
    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
    }
 
    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        return (tile != null && (tile instanceof TileSuperComputer));
    }
    
    public void resetStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    if (tile != null && (tile instanceof TileSuperComputer))
                        ((TileSuperComputer) tile).reset();
                }
    }
 
    public boolean hasMaster() {
        return hasMaster;
    }
 
    public boolean isMaster() {
        return isMaster;
    }
 
    public int getMasterX() {
        return masterX;
    }
 
    public int getMasterY() {
        return masterY;
    }
 
    public int getMasterZ() {
        return masterZ;
    }
 
    public void setHasMaster(boolean bool) {
        hasMaster = bool;
    }
 
    public void setIsMaster(boolean bool) {
        isMaster = bool;
    }
 
    public void setMasterCoords(int x, int y, int z) {
        masterX = x;
        masterY = y;
        masterZ = z;
    }
    
  //----------------------- inventory -----------------------
    private ItemStack[] contenu = new ItemStack[2];
    
    @Override
	public int getSizeInventory() {
		return this.contenu.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return this.contenu[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {
		if (this.contenu[slotIndex] != null){
            ItemStack itemstack;

            if (this.contenu[slotIndex].stackSize <= amount){
                itemstack = this.contenu[slotIndex];
                this.contenu[slotIndex] = null;
                this.markDirty();
                return itemstack;
            }
            else{
                itemstack = this.contenu[slotIndex].splitStack(amount);

                if (this.contenu[slotIndex].stackSize == 0){
                    this.contenu[slotIndex] = null;
                }

                this.markDirty();
                return itemstack;
            }
		}
		else{
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (this.contenu[slotIndex] != null){
            ItemStack itemstack = this.contenu[slotIndex];
            this.contenu[slotIndex] = null;
            return itemstack;
        }
        else{
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack stack){
		this.contenu[slotIndex] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}
	
	public void upgradeSlotUpdate(){
		if (this.contenu[1] != null && ModItem.basiccore.equals(this.contenu[1].getItem())&& corelv == 0){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	              corelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.hardenedcore.equals(this.contenu[1].getItem())&& corelv == 1){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	              corelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.reinforcedcore.equals(this.contenu[1].getItem())&& corelv == 2){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	              corelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.resonantcore.equals(this.contenu[1].getItem())&& corelv == 3){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	              corelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.basicstorage.equals(this.contenu[1].getItem())&& storagelv == 0 && corelv >= 1){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	        	  storagelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.hardenedstorage.equals(this.contenu[1].getItem())&& storagelv == 1 && corelv >= 2){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	        	  storagelv++;
	              this.contenu[1] = null;
	     }}
		if (this.contenu[1] != null && ModItem.reinforcedstorage.equals(this.contenu[1].getItem())&& storagelv == 2 && corelv >= 3){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	        	  storagelv++;
	              this.contenu[1] = null;
	     }}	
		if (this.contenu[1] != null && ModItem.resonantstorage.equals(this.contenu[1].getItem())&& storagelv == 3 && corelv >= 4){
	  		  if (this.contenu[1].stackSize <= 0){
	              this.contenu[1] = null;
	          }else{
	        	  storagelv++;
	              this.contenu[1] = null;
	     }}	
	}	
	
	public void updateStorage(){
		switch (storagelv) {
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
}