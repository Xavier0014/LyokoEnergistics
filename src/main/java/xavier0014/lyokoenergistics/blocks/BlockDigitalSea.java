package xavier0014.lyokoenergistics.blocks;

import javax.swing.Icon;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.proxy.ClientProxy;
import xavier0014.lyokoenergistics.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockDigitalSea extends BlockFluidClassic{
	
	private IIcon stillIcon, flowingIcon;
	
	public BlockDigitalSea(Fluid digitalSea, Material water,String name) {
		super(digitalSea, water);
		setBlockName(Reference.MOD_id +":"+name);
		this.setCreativeTab(LyokoEnergistics.lyokotab);
	}
	

	
	//public boolean canDisplace(IBlockAccess world, int x, int y, int z){
	//	if(world.getBlock(x, y, z).getMaterial().isLiquid()){
	//		return false;
	//	}
	//	return super.canDisplace(world, x, y, z);
	//}
	
	public IIcon getIcon(int side, int meta){
		if (side < 1) {
			return stillIcon;
		} else {
			return flowingIcon;
		}
	}
	
	public void registerIcons(IIconRegister register){
		stillIcon = register.registerIcon("lyokoenergistics:digitalSeaStill");
		flowingIcon = register.registerIcon("lyokoenergistics:digitalSeaFlow");
	}
	
	//public boolean displaceIfPossible(World world, int x, int y, int z){
	//	if(world.getBlock(x, y, z).getMaterial().isLiquid()){
	//		return false;
	//	}
	//	return super.displaceIfPossible(world, x, y, z);
	//}
	//protected String getUnwrappedUnlocalizedName(String unlocalizedName){
	//     return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	// }
	
	 public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		 if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			player.setHealth(0);
			//player.addChatMessage(player.getGameProfile().getName() );
		}else {
			entity.isDead = true;
		}
	 }

}
