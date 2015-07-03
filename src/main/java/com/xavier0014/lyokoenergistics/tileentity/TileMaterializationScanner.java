package com.xavier0014.lyokoenergistics.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMaterializationScanner extends TileEntityModelLE implements IInventory, IEnergyHandler{
	
	private ItemStack[] contenu = new ItemStack[2];
	private String customName;
	public String storedenergie = "0";
	
	
	@Override
    public void readFromNBT(NBTTagCompound compound){
	 super.readFromNBT(compound);

        if (compound.hasKey("CustomName", 8)){
            this.customName = compound.getString("CustomName");
        }
        
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.contenu = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i){
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.contenu.length){
                this.contenu[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
    	 super.writeToNBT(compound);
         NBTTagList nbttaglist = new NBTTagList();

         for (int i = 0; i < this.contenu.length; ++i){
             if (this.contenu[i] != null){
                 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                 nbttagcompound1.setByte("Slot", (byte)i);
                 this.contenu[i].writeToNBT(nbttagcompound1);
                 nbttaglist.appendTag(nbttagcompound1);
             }
         }

         compound.setTag("Items", nbttaglist);

         if (this.hasCustomInventoryName())
         {
             compound.setString("CustomName", this.customName);
         }
    }

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
		if (this.contenu[slotIndex] != null)
        {
            ItemStack itemstack = this.contenu[slotIndex];
            this.contenu[slotIndex] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack stack) {
		this.contenu[slotIndex] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "tile.materializationscanner";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
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
		storedenergie = String.valueOf(storage.getEnergyStored());
	}

	@Override
	public void closeInventory() {	
		storedenergie = "10";
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}
	
	private EnergyStorage storage = new EnergyStorage(50000,80,0);
	
	private int energie;

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		// TODO Auto-generated method stub
		return storage.getMaxEnergyStored();
	}
	
}
