package xavier0014.lyokoenergistics.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import xavier0014.lyokoenergistics.tileentity.TileLyokoForetMaster;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

public class LyokoForetMaster extends BlockLE{
	
    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon2,blockIcon3;
	
	public LyokoForetMaster(String name) {
		super(name);
	}
	
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	 
	@Override
	public TileEntity createTileEntity(World world, int metadata){
		return new TileLyokoForetMaster();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
	    blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	    blockIcon2 = iconRegister.registerIcon(String.format("%s2", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	    blockIcon3 = iconRegister.registerIcon(String.format("%s3", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		switch (TileLyokoForetMaster.progress) {
		case 0:
			return blockIcon;
		case 2:
			return blockIcon2;
		case 3:
			return blockIcon3;
		default:
			return blockIcon;
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack){
		world.setBlockToAir(x, y, z);
	}
}
