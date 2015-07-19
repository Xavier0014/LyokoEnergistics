package com.xavier0014.lyokoenergistics.tileentity;

import java.util.EnumSet;
import java.util.Iterator;

import appeng.api.AEApi;
import appeng.api.networking.GridFlags;
import appeng.api.networking.GridNotification;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridBlock;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridMultiblock;
import appeng.api.networking.IGridNode;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import appeng.api.util.DimensionalCoord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileSuperComputer extends TileEntityLE implements IEnergyHandler, IInventory, IGridHost, IGridBlock{
	
	private boolean hasMaster, isMaster;
    private int masterX, masterY, masterZ;
    public boolean onoff;
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("masterX", masterX);
        compound.setInteger("masterY", masterY);
        compound.setInteger("masterZ", masterZ);
        compound.setBoolean("hasMaster", hasMaster);
        compound.setBoolean("isMaster", isMaster);
        if (hasMaster() && isMaster()) {
        	compound.setBoolean("onoff", onoff);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
        hasMaster = compound.getBoolean("hasMaster");
        isMaster = compound.getBoolean("isMaster");
        if (hasMaster() && isMaster()) {
        	onoff = compound.getBoolean("onoff");
        }
    }
	
	
	@Override
    public void updateEntity() {
		System.out.println(onoff);
        if (!worldObj.isRemote) {
            if (hasMaster()) { 
                if (isMaster()) {
                
                }
            } else {
                if (checkMultiBlockForm())
                    setupStructure();
            }
        }
    }
	
	
	//--------------------- energy -------------------------
	
	private EnergyStorage storage = new EnergyStorage(100000,80,0);

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 0;
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
	
	//----------------------- network -----------------------

	@Override
	public double getIdlePowerUsage() {
		return 0;
	}

	@Override
	public EnumSet<GridFlags> getFlags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWorldAccessible() {
		return true;
	}

	@Override
	public DimensionalCoord getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AEColor getGridColor() {
		return AEColor.Black;
	}

	@Override
	public void onGridNotification(GridNotification notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNetworkStatus(IGrid grid, int channelsInUse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<ForgeDirection> getConnectableSides() {
		return EnumSet.of(ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
	}

	@Override
	public IGridHost getMachine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void gridChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getMachineRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGridNode getGridNode(ForgeDirection dir) {
		return AEApi.instance().createGridNode(this);
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection dir) {
		return getCableConnectionType(dir);
	}

	@Override
	public void securityBreak() {
		// TODO Auto-generated method stub
		
	}

}