package xavier0014.lyokoenergistics.tileentity;

import ibxm.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.mojang.authlib.GameProfile;

import scala.reflect.internal.Trees.If;
import xavier0014.lyokoenergistics.blocks.MaterializationScanner;
import xavier0014.lyokoenergistics.gui.GuiMaterializationScanner;
import xavier0014.lyokoenergistics.handler.Upgrade;
import xavier0014.lyokoenergistics.init.ModItem;
import xavier0014.lyokoenergistics.items.ItemDescriptionLE;
import xavier0014.lyokoenergistics.items.ItemLE;
import xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
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
	public double energiemultiplier = 1;
	public double energieEfficiency = 0;
	public static ArrayList<Boolean> craftlist = new ArrayList<Boolean>();
	public static HashMap<String, ArrayList<Boolean>> knowledge = new HashMap<String, ArrayList<Boolean>>();
	
    @Override
    public void writeToNBT(NBTTagCompound compound){
    	 super.writeToNBT(compound);
    	 savePlayerData(compound);
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
	 readPlayerData(compound);
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
    
    public void savePlayerData(NBTTagCompound compound) {
    	try {
    		int k = 0;
    		for (String i : knowledge.keySet()) {
    		    ArrayList<Boolean> playerKnow = knowledge.get(i);
    		    for (int j = 0; j < playerKnow.size(); j++) {
    		    	compound.setBoolean("knowledge"+i+j, playerKnow.get(j));
    			}
    		    compound.setString("pName"+k, i);
    		    k++;
    		    compound.setInteger("playerKownSize", playerKnow.size());
    		}
    		compound.setInteger("kownSize", knowledge.size());
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
			ArrayList<Boolean> l = new ArrayList<Boolean>();
			for (int j = 0; j < compound.getInteger("playerKownSize"); j++) {
				l.add(compound.getBoolean("knowledge"+i+j));
			}
			knowledge.put(i, l);
		}
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
		if (this.contenu[slotIndex] != null)
        {
            ItemStack itemstack;

            if (this.contenu[slotIndex].stackSize <= amount)
            {
                itemstack = this.contenu[slotIndex];
                this.contenu[slotIndex] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.contenu[slotIndex].splitStack(amount);

                if (this.contenu[slotIndex].stackSize == 0)
                {
                    this.contenu[slotIndex] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
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
	
	protected EnergyStorage storage = new EnergyStorage(51200000,32000,0);
	
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
			if (this.contenu[1] == null) {
				return true; 					
			}
			if (!this.contenu[1].isItemEqual(itemstack)) {
				return false;  
			}
			int result = contenu[1].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit(); 
			}
		return false;
	}
	
	Materials ae2_materials = AEApi.instance().materials();
	
	public int i = 0; 
	@Override
	public void updateEntity(){ //M�thode ex�cut�e � chaque tick
		if (!TileMaterializationScanner.knowledge.containsKey(playerName)) {
		 	ArrayList<Boolean> tempoArray = new ArrayList<Boolean>();
		 	for (int i = 0; i < RecipesMaterializationScanner.smelting().result.size(); i++) {
		 		tempoArray.add(false);
			}
		 	tempoArray.set(0, true);
    		TileMaterializationScanner.knowledge.put(playerName,tempoArray);
		}
		updateEnergyUsage();
		updateStorage();
		updateEnergyEfficiency();
		//if (!worldObj.isRemote) {
		//	storage.setEnergyStored(storage.getEnergyStored()+100);
		//}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		energiemultiplier = energiemultiplier - energieEfficiency;
		matterball = new ItemStack(ae2_materials.materialMatterBall.item());
		this.itemstack = RecipesMaterializationScanner.smelting().result.get(craft);
		if (Loader.isModLoaded("ExtraUtilities")) {
			this.matter = RecipesMaterializationScanner.smelting().matter.get(craft)*16;
		}else {
			this.matter = (int) RecipesMaterializationScanner.smelting().matter.get(craft)*2;
		}
		setWorkingTimeNeeded((int) ((RecipesMaterializationScanner.smelting().time.get(craft)/energieuse)*energiemultiplier));
    	switch (i) {
			case 1://output result
				this.smeltItem();
	    		i = 0;
				break;
			case 2://processing
				if (storage.getEnergyStored() > energieuse) {
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
    	upgradeSlotUpdate();
    	if (this.contenu[0] != null && !(this.contenu[0].getItem() instanceof ItemLE) && nomberMB <= 40000){
    		  if (this.contenu[0].stackSize <= 0){
                  this.contenu[0] = null;
              }else{
                 nomberMB = nomberMB + this.contenu[0].stackSize;
                 this.contenu[0] = null;
              }
		}
    }
	
	public void smeltItem(){
        if (this.canSmelt()){
             if (this.contenu[1] == null) {
                  this.contenu[1] = itemstack.copy(); 
             }
             else if (this.contenu[1].getItem() == itemstack.getItem()) {
                  this.contenu[1].stackSize += itemstack.stackSize;
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
	
	public void updateEnergyUsage(){
		switch (speedlv) {
		case 0:
			energieuse = 80;
			energiemultiplier = 1;
			break;
		case 1:
			energieuse = 200;
			energiemultiplier = 1.5;
			break;
		case 2:
			energieuse = 800;
			energiemultiplier = 3;
			break;
		case 3:
			energieuse = 8000;
			energiemultiplier = 8;
			break;
		case 4:
			energieuse = 32000;
			energiemultiplier = 16;
			break;
		default:
			energieuse = 80;
			break;
		}
	}
	
	public void updateEnergyEfficiency(){
			switch (Efficiencylv) {
			case 0:
				energieEfficiency = 0;
				break;
			case 1:
				energieEfficiency = 0.3;
				break;
			case 2:
				energieEfficiency = 1;
				break;
			case 3:
				energieEfficiency = 3;
				break;
			case 4:
				energieEfficiency = 8;
				break;
			default:
				energieEfficiency = 0;
				break;
			}
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
		if (contenu[0] != null) {
			corelv = BasiqueInputInt(this.contenu, 0, corelv,ModItem.basiccore, corelv == 0);
			corelv = BasiqueInputInt(this.contenu, 0, corelv,ModItem.hardenedcore, corelv == 1);
			corelv = BasiqueInputInt(this.contenu, 0, corelv, ModItem.reinforcedcore, corelv == 2);
			corelv = BasiqueInputInt(this.contenu, 0, corelv, ModItem.resonantcore, corelv == 3);
			speedlv = BasiqueInputInt(this.contenu, 0, speedlv, ModItem.basicspeed, speedlv == 0 && corelv >= 1);
			speedlv = BasiqueInputInt(this.contenu, 0, speedlv, ModItem.hardenedspeed, speedlv == 1 && corelv >= 2);
			speedlv = BasiqueInputInt(this.contenu, 0, speedlv, ModItem.reinforcedspeed, speedlv == 2 && corelv >= 3);
			speedlv = BasiqueInputInt(this.contenu, 0, speedlv, ModItem.resonantspeed, speedlv == 3 && corelv >= 4);
			storagelv = BasiqueInputInt(this.contenu, 0, storagelv, ModItem.basicstorage, storagelv == 0 && corelv >= 1);
			storagelv = BasiqueInputInt(this.contenu, 0, storagelv, ModItem.hardenedstorage, storagelv == 1 && corelv >= 2);
			storagelv = BasiqueInputInt(this.contenu, 0, storagelv, ModItem.reinforcedstorage, storagelv == 2 && corelv >= 3);
			storagelv = BasiqueInputInt(this.contenu, 0, storagelv, ModItem.resonantstorage, storagelv == 3 && corelv >= 4);
			Efficiencylv = BasiqueInputInt(this.contenu, 0, Efficiencylv, ModItem.basicefficacite, Efficiencylv == 0 && corelv >= 1 && energiemultiplier >= 1);
			Efficiencylv = BasiqueInputInt(this.contenu, 0, Efficiencylv, ModItem.hardenedefficacite, Efficiencylv == 1 && corelv >= 2 && energiemultiplier >= 1.2);
			Efficiencylv = BasiqueInputInt(this.contenu, 0, Efficiencylv, ModItem.reinforcedefficacite, Efficiencylv == 2 && corelv >= 3 && energiemultiplier >= 3);
			Efficiencylv = BasiqueInputInt(this.contenu, 0, Efficiencylv, ModItem.resonantefficacite, Efficiencylv == 3 && corelv >= 4 && energiemultiplier >= 8);
		}
	}	
}
