package xavier0014.lyokoenergistics.blocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import xavier0014.lyokoenergistics.proxy.ClientProxy;
import xavier0014.lyokoenergistics.tileentity.TileEntityLE;
import xavier0014.lyokoenergistics.tileentity.TileEntityModelLE;

public class BlockModelLE extends BlockLE{
	

	public BlockModelLE(String name) {
		super(name);
		
	}
	

	public boolean renderAsNormalBlock(){
	    return false;
	 }
	
	 public boolean isOpaqueCube(){
	     return false;
	 }
	 
	 public int getRenderType(){
	     return ClientProxy.tesrRenderId;
	 }
	 
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	 
	 public IIcon getIcon(int side, int metadata){
		return this.blockIcon;
	 }
	 
	 public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack){
	            TileEntity tile = world.getTileEntity(x, y, z);
	            if(tile instanceof TileEntityLE){
	                int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	                ((TileEntityModelLE)tile).setDirection((byte)direction);
	            }
	    }
		
}

