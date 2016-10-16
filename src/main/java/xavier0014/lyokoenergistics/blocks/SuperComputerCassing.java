package xavier0014.lyokoenergistics.blocks;

import xavier0014.lyokoenergistics.handler.IMultiBlockPart;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SuperComputerCassing extends BlockLE implements IMultiBlockPart{

	private IIcon top;

	public SuperComputerCassing(String name) {
		super(name);
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
