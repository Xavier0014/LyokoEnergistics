package xavier0014.lyokoenergistics.container;

import xavier0014.lyokoenergistics.recipes.SlotResult;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMaterializationScanner extends Container{
	
	private final TileMaterializationScanner tilems;
		public ContainerMaterializationScanner(TileMaterializationScanner tile,InventoryPlayer inventory) {
		this.tilems = tile;
		tile.openInventory();
		
	     this.addSlotToContainer(new Slot(tile, 0 , 41, 0));
	     this.addSlotToContainer(new SlotResult(tile, 1 , 41, 47));
	     
	     
		 this.bindPlayerInventory(inventory);
	}
	
	private void bindPlayerInventory(InventoryPlayer inventory){
		 for (int j = 0; j < 3; ++j){
	         for (int k = 0; k < 9; ++k){
	             this.addSlotToContainer(new Slot(inventory, k + j * 9 +9, 8 + k * 18 - 39, 103 + j * 18 -11));
	         }
	     }

	     for (int j = 0; j < 9; ++j){
	         this.addSlotToContainer(new Slot(inventory, j, 8 + j * 18 - 39, 150));
	     }
	 }
	
	


	@Override
	public boolean canInteractWith(EntityPlayer player) {
		
		return this.tilems.isUseableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotindex){
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotindex);

       if (slot != null && slot.getHasStack()){
           ItemStack itemstack1 = slot.getStack();
           itemstack = itemstack1.copy();

            if (slotindex < this.tilems.getSizeInventory()){
                if (!this.mergeItemStack(itemstack1, this.tilems.getSizeInventory(), this.inventorySlots.size(), true)){
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.tilems.getSizeInventory(), false)){
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
	
	public TileMaterializationScanner getTileMaterializationScanner(){
		return tilems;
	}
}
