package xavier0014.lyokoenergistics.items;

import appeng.api.features.INetworkEncodable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class VirtualEye extends ItemLE implements INetworkEncodable{

	public VirtualEye(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_){
		if (player.isSneaking()) {
//			switch (stack.getDisplayName()) {
//			case "TRANSMUTATION":
//				stack.setStackDisplayName("CREATION");
//				break;
//			case "CREATION":
//				stack.setStackDisplayName("TRANSMUTATION");
//				break;
//
//			default:
//				stack.setStackDisplayName("CREATION");
//				break;
//			}
			
		}else{
			if (stack.getDisplayName() == "CREATION") {
				switch (side) {
				case 0:
					world.setBlock(x, y-1, z, Blocks.cobblestone);
					break;
				case 1:
					world.setBlock(x, y+1, z, Blocks.cobblestone);
					break;
				case 2:
					world.setBlock(x, y, z-1, Blocks.cobblestone);
					break;
				case 3:
					world.setBlock(x, y, z+1, Blocks.cobblestone);
					break;
				case 4:
					world.setBlock(x-1, y, z, Blocks.cobblestone);
					break;
				case 5:
					world.setBlock(x+1, y, z, Blocks.cobblestone);
					break;
				default:
					break;
				}
			}else if(stack.getDisplayName() == "TRANSMUTATION"){
				if (y != 0) {
					world.setBlock(x, y, z, Blocks.cobblestone);
				}
			}
		}
		 return true;
	}

	@Override
	public String getEncryptionKey(ItemStack item) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
		}
		return item.getTagCompound().getString("key");
	}

	@Override
	public void setEncryptionKey(ItemStack item, String encKey, String name) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = item.getTagCompound();
		tag.setString("key", encKey);
		
	}
}
