package com.xavier0014.lyokoenergistics.tileentity;

import com.xavier0014.lyokoenergistics.blocks.MaterializationScanner;
import com.xavier0014.lyokoenergistics.gui.GuiMaterializationScanner;
import com.xavier0014.lyokoenergistics.init.ModItem;
import com.xavier0014.lyokoenergistics.items.ItemLE;
import com.xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import appeng.api.definitions.Materials;
import appeng.api.AEApi;

public class TileMaterializationScanner extends TileEntityModelLE implements IInventory, IEnergyHandler{
	
	private ItemStack[] contenu = new ItemStack[2];
	private String customName;
	private int workingTime = 0; 
	private int workingTimeNeeded;
	public int craft = 0; 
	public int nomberMB;
	public int energieuse = 80;

	
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
         
         compound.setTag("Items", nbttaglist);
         compound.setShort("workingTime",(short)this.workingTime); 
         compound.setShort("workingTimeNeeded", (short)this.workingTimeNeeded);
         compound.setShort("nomberMB", (short)this.nomberMB);
         compound.setShort("craft", (short)this.craft);
         compound.setShort("matter", (short)this.matter);
         storage.writeToNBT(compound);
    }
    
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
        this.workingTime = compound.getShort("workingTime"); //On lit nos valeurs
        this.workingTimeNeeded = compound.getShort("workingTimeNeeded");
        this.nomberMB = compound.getShort("nomberMB");
        this.craft = compound.getShort("craft");
        this.matter = compound.getShort("matter");
        storage.readFromNBT(compound);
       
    }
    
    //-----------------------------------------------------------------------------
    

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
	}

	@Override
	public void closeInventory() {	
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack stack) {
		return true;
	}
	
	//-----------------------------------------------------------------------------
	
	private EnergyStorage storage = new EnergyStorage(100000,80,0);
	
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
		return storage.getMaxEnergyStored();
	}
	
	//-----------------------------------------------------------------------------
	public ItemStack itemstack;
	private ItemStack matterball;
	public int matter;
	
	public boolean isProcessing(){
		return this.workingTime > 0;
	}

	private boolean canSmelt(){
		if(nomberMB >= matter){
			if (itemstack == null) {
				return false; 						
			}
			if (this.contenu[0] == null) {
				return true; 					
			}
			if (!this.contenu[0].isItemEqual(itemstack)) {
				return false;  
			}
			int result = contenu[0].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit(); 
			}
		return false;
	}
	
	Materials ae2_materials = AEApi.instance().materials();
	
	public int i = 0; 
	@Override
	public void updateEntity(){ //M�thode ex�cut�e � chaque tick
		matterball = new ItemStack(ae2_materials.materialMatterBall.item());
		this.itemstack = RecipesMaterializationScanner.smelting().result.get(craft);
		this.matter = RecipesMaterializationScanner.smelting().matter.get(craft);
		setWorkingTimeNeeded((Integer) RecipesMaterializationScanner.smelting().time.get(craft)/energieuse);
    	switch (i) {
			case 1://output result
				this.smeltItem();
	    		i = 0;
				break;
			case 2://processing
				if (storage.getEnergyStored() > 200) {
					this.workingTime++;
					storage.modifyEnergyStored(-energieuse);
					if(this.workingTime >= this.workingTimeNeeded){
						i = 1;
					}
				}else if(!(nomberMB >= matter)){
					i =0;
				}
	    		
				break;
			case 3://start process
				storage.modifyEnergyStored(-energieuse);
				this.workingTime = 1; 
	    		i = 2;
				break;
		default:
            this.workingTime= 0; 
			if(this.canSmelt() && !this.isProcessing() && storage.getEnergyStored() > 200){
				i = 3;
			}
			break;
		}
    	
    	if (this.contenu[1] != null && matterball.getItem().equals(this.contenu[1].getItem())){
    		  if (this.contenu[1].stackSize <= 0){
                  this.contenu[1] = null;
              }else{
                 nomberMB = nomberMB + this.contenu[1].stackSize;
                 this.contenu[1] = null;
              }
		}
    	
    }
	
	public void smeltItem(){
        if (this.canSmelt()){
             if (this.contenu[0] == null) {
                  this.contenu[0] = itemstack.copy(); 
             }
             else if (this.contenu[0].getItem() == itemstack.getItem()) {
                  this.contenu[0].stackSize += itemstack.stackSize;
             }
             nomberMB = nomberMB - matter;
        }
    }
	

	
	public void setWorkingTimeNeeded(int time){
		this.workingTimeNeeded = time;
	}
	
	public int getWorkingTimeNeeded(){
		return workingTimeNeeded;
	}
	
	public int getWorkingTime(){
		return workingTime;
	}
	
}
