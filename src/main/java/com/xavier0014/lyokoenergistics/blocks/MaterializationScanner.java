package com.xavier0014.lyokoenergistics.blocks;

import com.xavier0014.lyokoenergistics.proxy.ClientProxy;
import com.xavier0014.lyokoenergistics.tileentity.TileEntityLE;
import com.xavier0014.lyokoenergistics.tileentity.TileEntityModelLE;
import com.xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MaterializationScanner extends BlockModelLE{

	public MaterializationScanner(String name) {
		super(name);
	}  
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
	    return new TileMaterializationScanner();
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileMaterializationScanner)
        {
        	TileMaterializationScanner tileTuto = (TileMaterializationScanner)tile;
            switch(tileTuto.getDirection())
            {
                case 0:
                    this.setBlockBounds(0.06F, 0.0F, 0.0F, 0.94F, 0.75F, 0.94F); //valeur pour la direction 0
                    break;
                case 1:
                    this.setBlockBounds(0.06F, 0.0F, 0.06F, 1.0F, 0.75F, 0.94F); //valeur pour la direction 1
                    break;
                case 2:
                    this.setBlockBounds(0.06F, 0.0F, 0.06F, 0.94F, 0.75F, 1.0F); //valeur pour la direction 2
                    break;
                case 3:
                    this.setBlockBounds(0.0F, 0.0F, 0.06F, 0.94F, 0.75F, 0.94F); //valeur pour la direction 3
                    break;
            }
        }
    }
}
