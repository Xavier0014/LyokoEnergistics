package xavier0014.lyokoenergistics.tileentity;

import java.util.EnumSet;
import java.util.Iterator;

import xavier0014.lyokoenergistics.init.ModItem;

import appeng.api.AEApi;
import appeng.api.implementations.IPowerChannelState;
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
    public int masterX, masterY, masterZ;
    public boolean onoff;
    public TileSuperComputer mastertile;
    
    public TileSuperComputer(){
    	
    }
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("onoff", onoff);
        compound.setBoolean("hasMaster", hasMaster);
        compound.setBoolean("isMaster", isMaster);
        compound.setInteger("masterX", masterX);
        compound.setInteger("masterY", masterY);
        compound.setInteger("masterZ", masterZ);
        if (hasMaster() && isMaster()) {
        	
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        onoff = compound.getBoolean("onoff");
        hasMaster = compound.getBoolean("hasMaster");
        isMaster = compound.getBoolean("isMaster");
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
        if (hasMaster() && isMaster()) {
        	
        }
    }
	
	
	@Override
    public void updateEntity() {
		upgradeSlotUpdate();
		updateValue();
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
	
	public void updateValue() {
		if (mastertile != null &&!hasMaster && !isMaster) {
			this.onoff = mastertile.onoff;
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
	
	//----------------------- network -----------------------
	IGridNode node = null;
	IGridBlock gridblock;
	private boolean isActive;
	
	@Override
	public double getIdlePowerUsage() {
		System.out.println("1");
		return 3.0D;
	}

	@Override
	public EnumSet<GridFlags> getFlags() {
		System.out.println("2");
		return EnumSet.of(GridFlags.REQUIRE_CHANNEL);
	}

	@Override
	public boolean isWorldAccessible() {
		System.out.println("3");
		return true;
	}

	@Override
	public DimensionalCoord getLocation() {
		System.out.println("4");
		return new DimensionalCoord(this);
	}

	@Override
	public AEColor getGridColor() {
		System.out.println("5");
		return AEColor.Transparent;
	}

	@Override
	public void onGridNotification(GridNotification notification) {
		System.out.println("6");
		
		
	}

	@Override
	public void setNetworkStatus(IGrid grid, int channelsInUse) {
		System.out.println("7");
		
	}

	@Override
	public EnumSet<ForgeDirection> getConnectableSides() {
		System.out.println("8");
		return EnumSet.of(ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
	}

	@Override
	public IGridHost getMachine() {
		System.out.println("9");
		return this;
	}

	@Override
	public void gridChanged() {
		System.out.println("10");
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getMachineRepresentation() {
		System.out.println("11");
		// TODO Auto-generated method stub
		return new ItemStack(this.getWorldObj().getBlock(xCoord, yCoord, zCoord),this.getBlockMetadata());
	}

	@Override
	public IGridNode getGridNode(ForgeDirection dir) {
		System.out.println("12");
		if(this.worldObj.isRemote)
			return null;
		if(node == null){
			node = AEApi.instance().createGridNode(this);
			node.updateState();
		}
		return node;
	}

	@Override
	public AECableType getCableConnectionType(ForgeDirection dir) {
		System.out.println("13");
		return AECableType.SMART;
	}

	@Override
	public void securityBreak() {
		System.out.println("14");
	}
}