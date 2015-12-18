package xavier0014.lyokoenergistics.blocks;

import javax.swing.Icon;

import com.mojang.authlib.GameProfile;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.init.ModBlock;
import xavier0014.lyokoenergistics.proxy.ClientProxy;
import xavier0014.lyokoenergistics.reference.Reference;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BlockDigitalSea extends BlockFluidClassic{
	
	private IIcon stillIcon, flowingIcon;
	
	public BlockDigitalSea(Fluid digitalSea, Material water,String name) {
		super(digitalSea, water);
		setBlockName(Reference.MOD_id +":"+name);
		this.setCreativeTab(LyokoEnergistics.lyokotab);
	}
	
	public IIcon getIcon(int side, int meta){
		if (side < 1) {
			return stillIcon;
		} else {
			return flowingIcon;
		}
	}
	
	public void registerBlockIcons(IIconRegister register){
		stillIcon = register.registerIcon("lyokoenergistics:digitalSeaStill");
		flowingIcon = register.registerIcon("lyokoenergistics:digitalSeaFlow");
	}
	
	
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
