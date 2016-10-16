package xavier0014.lyokoenergistics.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TowerWall extends BlockLE{

	public TowerWall(String name) {
		super(name,Material.fire);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
	    blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	
	
	 public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z){
		   return null;
	    }
	 
	 

	 
	 public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		 if(entity instanceof EntityLivingBase){
			 ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(2, 1, 4, true));
			 ((EntityLivingBase) entity).setSprinting(false);
		 }
			
	 }
}
