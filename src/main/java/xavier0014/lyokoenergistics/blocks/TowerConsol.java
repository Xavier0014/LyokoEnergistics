package xavier0014.lyokoenergistics.blocks;

import java.util.ArrayList;
import java.util.Arrays;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.init.ModItem;
import xavier0014.lyokoenergistics.tileentity.TileEntityLE;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import xavier0014.lyokoenergistics.tileentity.TileTowerConsol;

public class TowerConsol extends BlockLE{
	
	public TowerConsol(String name) {
		super(name);
	}
	
	 @Override
	 public boolean hasTileEntity(int metadata){
	     return true;
	 }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
		return new TileTowerConsol();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz){
		TileEntity tile = world.getTileEntity(x, y, z);
		 if (tile != null && tile instanceof TileTowerConsol) {
			 TileTowerConsol tiletc = (TileTowerConsol) tile;
			 if (tiletc.playerName == "") {
	      			GameProfile gameprofile = player.getGameProfile();
	  	        	tiletc.setPlayerName(gameprofile.getName());
				} 
			 try {
				 if (TileSuperComputerControler.playersList.get(tiletc.playerName) != 0) {
					player.openGui(LyokoEnergistics.instance, 2, world, x, y, z);
			            return true;
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				TileSuperComputerControler.playersList.put(tiletc.playerName, 0);
				 return false;
			}
			 
		 }
		 return true;
	}
}
