package xavier0014.lyokoenergistics.blocks;

import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;

import com.mojang.authlib.GameProfile;
import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SuperComputer extends BlockLE{
	
	private IIcon top;

	public SuperComputer(String name) {
		super(name);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz){
		TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileSuperComputer) {
        	TileSuperComputer tilesc = (TileSuperComputer) tile;
            if (tilesc.hasMaster()) {
            	if (tilesc.playerName == "") {
         			GameProfile gameprofile = player.getGameProfile();
     	        	tilesc.setPlayerName(gameprofile.getName());
				}
            	if (!world.isRemote){
            		player.openGui(LyokoEnergistics.instance, 1, world, tilesc.getMasterX(), tilesc.getMasterY(), tilesc.getMasterZ());
            		return true;
            	}
            }
        }
		return true;
	}
	
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	 
	 @Override
	    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
	        TileEntity tile = world.getTileEntity(x, y, z);
	        if (tile != null && tile instanceof TileSuperComputer) {
	        	TileSuperComputer multiBlock = (TileSuperComputer) tile;
	            if (multiBlock.hasMaster()) {
	                if (multiBlock.isMaster()) {
	                    if (!multiBlock.checkMultiBlockForm())
	                        multiBlock.resetStructure();
	                } else {
	                    if (!multiBlock.checkForMaster()) {
	                        multiBlock.reset();
	                        world.markBlockForUpdate(x, y, z);
	                    }
	                }
	            }
	        }
	        super.onNeighborBlockChange(world, x, y, z, block);
	    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
	    return new TileSuperComputer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
	    this.blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	    this.top = iconRegister.registerIcon(String.format("%stop", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		if (side == 0||side == 1) {
			return this.top;
		}else{
			return this.blockIcon;
		}
	}
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
		if(meta == 0){
			if(world.isRemote)
				return;
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null){
				if(tile instanceof IGridHost){
					((IGridHost) tile).getGridNode(ForgeDirection.UNKNOWN).updateState();
				}
			}
		}
	}
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		if(meta == 0){
			if(world.isRemote)
				return;
			TileEntity tile  = world.getTileEntity(x, y, z);
			if(tile != null){
				if(tile instanceof IGridHost){
					IGridNode  node =((IGridHost) tile).getGridNode(ForgeDirection.UNKNOWN);
					if(node != null){
						node.destroy();
					}
				}
			}
		}
	}
	

}
