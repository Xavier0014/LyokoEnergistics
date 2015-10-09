package xavier0014.lyokoenergistics.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import xavier0014.lyokoenergistics.init.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;



public class VirtualisationScanner extends BlockLE{

	public VirtualisationScanner(String name) {
		super(name);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack){
		if (!checkSpace(world,x,y,z)) {
			world.setBlockToAir(x, y, z);
			stack.stackSize++;
		}else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 6; j++) {
					for (int k = 0; k < 3; k++) {
						world.setBlock(x-1+i, y+j, z-1+k, ModBlock.virtualisationScanner);
					}
				}
			}
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata){
		if (world.getBlock(x, y + 1, z) instanceof VirtualisationScanner) {
			world.setBlockToAir(x, y+1, z);
		}else if (world.getBlock(x +1, y - 5, z) instanceof VirtualisationScanner) {
			world.setBlockToAir(x +1, y - 5, z);
		}else if (world.getBlock(x - 2, y - 5, z + 1) instanceof VirtualisationScanner) {
			world.setBlockToAir(x -2, y - 5, z+1);
		}else if (world.getBlock(x - 2, y - 5, z - 2) instanceof VirtualisationScanner) {
			world.setBlockToAir(x -2, y - 5, z-2);
		}
	}
	public boolean checkSpace(World world, int x, int y, int z){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					if (!world.isAirBlock(x-1+i, y+j, z-1+k)) {
						if (!(x-1+i == x && y+j == y && z-1+k == z)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}
