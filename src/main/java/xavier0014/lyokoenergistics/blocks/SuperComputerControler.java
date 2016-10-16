package xavier0014.lyokoenergistics.blocks;

import java.util.ArrayList;

import com.mojang.authlib.GameProfile;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.handler.IMultiBlockPart;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SuperComputerControler extends BlockLE implements IMultiBlockPart{

	private IIcon top;

	public SuperComputerControler(String name) {
		super(name);
	}
	
	@Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
	    return new TileSuperComputerControler();
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
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz){
		TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileSuperComputerControler) {
        	TileSuperComputerControler tilesc = (TileSuperComputerControler) tile;
            	if (tilesc.playerName == "") {
         			GameProfile gameprofile = player.getGameProfile();
         			tilesc.setPlayerName(gameprofile.getName());
     	        	TileSuperComputerControler.playersList.put(gameprofile.getName(), 0);
				}
            		player.openGui(LyokoEnergistics.instance, 1, world, tilesc.xCoord, tilesc.yCoord, tilesc.zCoord);
            		return true;
        }
		return true;
	}
	
}
