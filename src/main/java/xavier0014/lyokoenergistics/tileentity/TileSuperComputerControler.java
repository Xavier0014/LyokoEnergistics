package xavier0014.lyokoenergistics.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import cofh.api.energy.EnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xavier0014.lyokoenergistics.blocks.SuperComputerCassing;
import xavier0014.lyokoenergistics.blocks.SuperComputerEnergyInput;
import xavier0014.lyokoenergistics.handler.IMultiBlockPart;
import xavier0014.lyokoenergistics.init.ModItem;


public class TileSuperComputerControler extends TileEntityLE implements IInventory{
	
    public static HashMap<String, Integer> playersList = new HashMap<String, Integer>();
	public EnergyStorage storage = new EnergyStorage(100000,32000,0);
	public boolean isMultiBlockForm;
	public boolean onoff;
	public TileSuperComputerEnergyInput energyinp;
    public static final Boolean[] template = {false};
	
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		onoff = compound.getBoolean("onoff");
    	readPlayerData(compound);
	}
	
	
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
    	compound.setBoolean("onoff", onoff);
    	savePlayerData(compound);
	}	
	
	 public void savePlayerData(NBTTagCompound compound) {
	    	try {
	    		int k = 0;
	    		for (String i : playersList.keySet()) {
	    		    int playerKnow = playersList.get(i);
	    		    	compound.setInteger("knowledge"+i, playerKnow);
	    		    compound.setString("pName"+k, i);
	    		    k++;
	    		}
	    		compound.setInteger("kownSize", playersList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		public void readPlayerData(NBTTagCompound compound) {
			ArrayList<String> playerList = new ArrayList<String>(); 
			for (int k = 0; k < compound.getInteger("kownSize"); k++) {
				playerList.add(compound.getString("pName"+k));
			}
			for (String i : playerList) {
				int l = compound.getInteger("knowledge"+i);
				playersList.put(i, l);
			}
		}
	private boolean ismodify;
	private int i;
	public void updateEntity() {
		if (energyinp != null && energyinp.storage.getEnergyStored() <= 0) {
			onoff = false;
		}
		if (onoff) {
			if (!ismodify) {
				modifyEnergyByPlayer(playerName, 100);
				ismodify = true;
			}
			modifyEnergyStored(-playersList.get(playerName));
    	}else {
    		playersList.put(playerName, 0);
    		ismodify = false;
		}
		if(i == 20){
			checkMultiBlockStructure();
			upgradeSlotUpdate();
		}
		if(i == 20)
			i = 0;
		i++;
	}
	
	//--------------upgrade--------------------------
	
	public int BasiqueInputInt(ItemStack[] contenuL,int slot,int upgrade,Item item,boolean condition) {
		if (contenuL[slot]!= null && item.equals(contenuL[slot].getItem()) &&condition){
	  		  if (contenuL[slot] != null && contenuL[slot].stackSize <= 0){
	  			  contenuL[slot] = null;
	          }else{
	        	  upgrade++;
	              contenuL[slot] = null;
	     }}
		return upgrade;
	}
	
	public void upgradeSlotUpdate(){
		if (contenu[1] != null) {
			corelv = BasiqueInputInt(this.contenu, 1, corelv,ModItem.basiccore, corelv == 0);
			corelv = BasiqueInputInt(this.contenu, 1, corelv,ModItem.hardenedcore, corelv == 1);
			corelv = BasiqueInputInt(this.contenu, 1, corelv, ModItem.reinforcedcore, corelv == 2);
			corelv = BasiqueInputInt(this.contenu, 1, corelv, ModItem.resonantcore, corelv == 3);
			storagelv = BasiqueInputInt(this.contenu, 1, storagelv, ModItem.basicstorage, storagelv == 0 && corelv >= 1);
			storagelv = BasiqueInputInt(this.contenu, 1, storagelv, ModItem.hardenedstorage, storagelv == 1 && corelv >= 2);
			storagelv = BasiqueInputInt(this.contenu, 1, storagelv, ModItem.reinforcedstorage, storagelv == 2 && corelv >= 3);
			storagelv = BasiqueInputInt(this.contenu, 1, storagelv, ModItem.resonantstorage, storagelv == 3 && corelv >= 4);
			Efficiencylv = BasiqueInputInt(this.contenu, 1, Efficiencylv, ModItem.basicefficacite, Efficiencylv == 0 && corelv >= 1);
			Efficiencylv = BasiqueInputInt(this.contenu, 1, Efficiencylv, ModItem.hardenedefficacite, Efficiencylv == 1 && corelv >= 2);
			Efficiencylv = BasiqueInputInt(this.contenu, 1, Efficiencylv, ModItem.reinforcedefficacite, Efficiencylv == 2 && corelv >= 3);
			Efficiencylv = BasiqueInputInt(this.contenu, 1, Efficiencylv, ModItem.resonantefficacite, Efficiencylv == 3 && corelv >= 4);
		}
	}
	
	//--------------energy------------------------
	
	
	public int getEnergyStored(){
		if (energyinp != null)
			return energyinp.getEnergyStored(null);
		return 0;
	}
	
	public int getMaxEnergyStored(){
		if (energyinp != null)
			return energyinp.getMaxEnergyStored(null);
		return 1;
	}
	
	public void modifyEnergyStored(int energy){
		if (energyinp != null)
			energyinp.storage.setEnergyStored(getEnergyStored()+energy);
	}
	
	/*
	 * @param energy 
	 * 
	 */
	public static void modifyEnergyByPlayer(String player, int energy) {
		int i = playersList.get(player);
		playersList.put(player, i + energy);
	}
	
	
	//----------------------- multiblock -----------------------
	
	private void checkMultiBlockStructure() {
		switch (findControlerSide()) {
		case 0:
			if(checkIMultiBlockPart(xCoord-1, zCoord-2 , xCoord+2, zCoord+1) && worldObj.getBlock(xCoord, yCoord+1, zCoord-1) instanceof SuperComputerCassing && worldObj.getBlock(xCoord, yCoord+2, zCoord-1) instanceof SuperComputerCassing){
				isMultiBlockForm = true;
			}else {
				isMultiBlockForm = false;
			}
				
			break;
		case 1:
			if(checkIMultiBlockPart(xCoord, zCoord-1 , xCoord+3, zCoord+2) && worldObj.getBlock(xCoord+1, yCoord+1, zCoord) instanceof SuperComputerCassing && worldObj.getBlock(xCoord+1, yCoord+2, zCoord) instanceof SuperComputerCassing){
				isMultiBlockForm = true;
			}else {
				isMultiBlockForm = false;
			}
			break;
		case 2:
			if(checkIMultiBlockPart(xCoord-1, zCoord , xCoord+2, zCoord+3) && worldObj.getBlock(xCoord, yCoord+1, zCoord+1) instanceof SuperComputerCassing && worldObj.getBlock(xCoord, yCoord+2, zCoord+1) instanceof SuperComputerCassing){
				isMultiBlockForm = true;
			}else {
				isMultiBlockForm = false;
			}
			break;
		case 3:
			if(checkIMultiBlockPart(xCoord-2, zCoord-1 , xCoord+1, zCoord+2) && worldObj.getBlock(xCoord-1, yCoord+1, zCoord) instanceof SuperComputerCassing && worldObj.getBlock(xCoord-1, yCoord+2, zCoord) instanceof SuperComputerCassing){
				isMultiBlockForm = true;
			}else {
				isMultiBlockForm = false;
			}
			break;
		default:
			break;
		}
	}
	
	private int findControlerSide(){
		if (!(worldObj.getBlock(xCoord, yCoord, zCoord+1) instanceof SuperComputerCassing)) return 0;
        if (!(worldObj.getBlock(xCoord-1, yCoord, zCoord) instanceof SuperComputerCassing)) return 1;
        if (!(worldObj.getBlock(xCoord, yCoord, zCoord-1) instanceof SuperComputerCassing)) return 2;
        if (!(worldObj.getBlock(xCoord+1, yCoord, zCoord) instanceof SuperComputerCassing)) return 3;
        return 0;
	}
	
	public boolean checkIMultiBlockPart(int x1, int z1, int x2, int z2) {
		for (int i = x1; i < x2; i++) {
			for (int j = z1; j < z2; j++) {
				if (!(worldObj.getBlock(i, yCoord, j) instanceof IMultiBlockPart))return false;
				if (worldObj.getBlock(i, yCoord, j) instanceof SuperComputerEnergyInput){
					((TileSuperComputerEnergyInput)worldObj.getTileEntity(i, yCoord, j)).controlerTile = this;
					energyinp = (TileSuperComputerEnergyInput)worldObj.getTileEntity(i, yCoord, j);
				}
			}
		}
		return true;
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
	
	
}
