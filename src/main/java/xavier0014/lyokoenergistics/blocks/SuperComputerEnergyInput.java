package xavier0014.lyokoenergistics.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import xavier0014.lyokoenergistics.handler.IMultiBlockPart;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerEnergyInput;

public class SuperComputerEnergyInput extends BlockLE implements IMultiBlockPart{

	private IIcon top;

	public SuperComputerEnergyInput(String name) {
		super(name);
	}
	
	@Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
	    return new TileSuperComputerEnergyInput();
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
}
