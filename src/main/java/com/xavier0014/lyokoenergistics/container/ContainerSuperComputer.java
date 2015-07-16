package com.xavier0014.lyokoenergistics.container;

import com.xavier0014.lyokoenergistics.recipes.SlotResult;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import com.xavier0014.lyokoenergistics.tileentity.TileSuperComputer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSuperComputer extends Container{
	private final TileSuperComputer tilesc;
	
	public ContainerSuperComputer(TileSuperComputer tile,InventoryPlayer inventory) {
		this.tilesc = tile;
		tile.openInventory();
		
	     this.addSlotToContainer(new Slot(tile, 1 , 6, 0));
	     
	     
		 this.bindPlayerInventory(inventory);
	}
	
	private void bindPlayerInventory(InventoryPlayer inventory){
		 for (int j = 0; j < 3; ++j){
	         for (int k = 0; k < 9; ++k){
	             this.addSlotToContainer(new Slot(inventory, k + j * 9 +9, 8 + k * 18 - 1, 103 + j * 18 -11));
	         }
	     }

	     for (int j = 0; j < 9; ++j){
	         this.addSlotToContainer(new Slot(inventory, j, 8 + j * 18 - 1, 150));
	     }
	 }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tilesc.isUseableByPlayer(player);
	}
	
	public TileSuperComputer getTileSuperComputer(){
		return tilesc;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotindex){
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotindex);

       if (slot != null && slot.getHasStack()){
           ItemStack itemstack1 = slot.getStack();
           itemstack = itemstack1.copy();

            if (slotindex < this.tilesc.getSizeInventory()){
                if (!this.mergeItemStack(itemstack1, this.tilesc.getSizeInventory(), this.inventorySlots.size(), true)){
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.tilesc.getSizeInventory(), false)){
                return null;
            }

            if (itemstack1.stackSize == 0){
                slot.putStack((ItemStack)null);
            }
            else{
                slot.onSlotChanged();
            }
        }

        return itemstack;
	}

}
